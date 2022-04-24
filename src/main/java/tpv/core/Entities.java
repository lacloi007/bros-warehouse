package tpv.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.jdbc.core.RowMapper;

import lombok.Getter;
import lombok.Setter;
import tpv.bros.common.mapper.EntityMapper;
import tpv.bros.common.table.Entity;
import tpv.core.annotation.Column;
import tpv.core.annotation.Mapper;
import tpv.core.annotation.Table;

@SuppressWarnings({"unchecked", "rawtypes"})
public class Entities {
	final static Map<String, Class<? extends Entity>> MAP_PREFIXS = new HashMap<>();
	final static Map<Class<? extends Entity>, TableInfo> MAP_ENTITIES = new HashMap<>();
	static {
		// prepare class path provider
		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));

		// prepare bean definitions
		Set<BeanDefinition> entityClasses = new LinkedHashSet<>();
		Set.of(Entity.class.getPackageName(), EntityMapper.class.getPackageName())
			.forEach(i -> entityClasses.addAll(provider.findCandidateComponents(i)));

		// process each of bean definition
		for (BeanDefinition bean: entityClasses) {
			try {
				Class<?> nativeClass = Class.forName(bean.getBeanClassName());
				if (nativeClass.isAnnotationPresent(Table.class)) {
					Class<? extends Entity> entity = (Class<? extends Entity>) nativeClass;
					TableInfo information = new TableInfo(entity);
					MAP_ENTITIES.put(entity, information);
					MAP_PREFIXS.put(information.prefix, entity);
				}

				if (nativeClass.isAnnotationPresent(Mapper.class)) {
					Mapper mapper = nativeClass.getAnnotation(Mapper.class);
					TableInfo information = MAP_ENTITIES.getOrDefault(mapper.value(), null);
					if (information != null)
						information.setRowMapper((Class<? extends RowMapper>) nativeClass);
				}
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/******************************************
	 * PUBLIC METHODS
	 ******************************************/
	public static TableInfo tblInfo(Class<? extends Entity> entityClass) {
		return MAP_ENTITIES.getOrDefault(entityClass, null);
	}

	/******************************************
	 * CLASS DEFINITION
	 ******************************************/
	public static class TableInfo {
		final Class<? extends Entity> entity;
		@Getter String name, prefix;
		@Getter @Setter Class<? extends RowMapper> rowMapper;
		public final Map<String, FieldInfo> declaredFields = new LinkedHashMap<>();
		public final Map<String, FieldInfo> databaseFields = new LinkedHashMap<>();

		public TableInfo(Class<? extends Entity> entity) {
			Table annotation = entity.getAnnotation(Table.class);
			this.entity = entity;
			this.name = annotation.name();
			this.prefix = annotation.prefix();
			this.initialize();
		}

		public <T extends Entity> RowMapper<T> getMapper() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
			return (RowMapper<T>) rowMapper.getConstructor().newInstance();
		}

		private void initialize() {
			for (Field field: entity.getDeclaredFields()) {
				// prepare column information
				if (field.isAnnotationPresent(Column.class)) {
					FieldInfo info = new FieldInfo(field);
					this.declaredFields.put(info.getDeclaredField(), info);
					this.databaseFields.put(info.getDatabaseField(), info);
				}
			}
		}
	}

	public static class FieldInfo {
		@Getter String declaredField;
		@Getter Column column;

		public FieldInfo(Field field) {
			this.declaredField = field.getName();
			this.column = field.getAnnotation(Column.class);
		}

		public String getDatabaseField() { return column.name(); }
	}
}
