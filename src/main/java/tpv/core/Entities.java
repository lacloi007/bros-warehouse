package tpv.core;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.jdbc.core.RowMapper;

import lombok.Getter;
import lombok.Setter;
import tpv.bros.common.table.Entity;
import tpv.core.annotation.Mapper;
import tpv.core.annotation.Table;

@SuppressWarnings("unchecked")
public class Entities {
	final static ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
	final static Map<String, Class<? extends Entity>> MAP_PREFIXS = new HashMap<>();
	final static Map<Class<? extends Entity>, TableInformation> MAP_ENTITIES = new HashMap<>();
	final static String TABLE_BASE_PACKAGE = "tpv.bros.common.table";
	static {
		Set<BeanDefinition> entityClasses = new LinkedHashSet<>(); {
			entityClasses.addAll(provider.findCandidateComponents(Entity.class.getPackageName()));
			entityClasses.addAll(provider.findCandidateComponents(TABLE_BASE_PACKAGE));
			entityClasses.addAll(provider.findCandidateComponents("tpv.bros.common.mapper"));
		}

		for (BeanDefinition bean: entityClasses) {
			try {
				Class<?> nativeClass = Class.forName(bean.getBeanClassName());
				if (nativeClass.isAnnotationPresent(Table.class)) {
					Class<? extends Entity> entity = (Class<? extends Entity>) nativeClass;
					TableInformation information = new TableInformation(entity);
					MAP_ENTITIES.put(entity, information);
					MAP_PREFIXS.put(information.prefix, entity);
				}

				if (nativeClass.isAnnotationPresent(Mapper.class)) {
					Mapper mapper = nativeClass.getAnnotation(Mapper.class);
					TableInformation information = MAP_ENTITIES.getOrDefault(mapper.value(), null);
					if (information != null)
						information.setRowMapper((Class<? extends RowMapper<?>>) nativeClass);
				}
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/******************************************
	 * PUBLIC METHODS
	 ******************************************/
	public static TableInformation tblInfo(Class<? extends Entity> entityClass) { return MAP_ENTITIES.getOrDefault(entityClass, null); }

	/******************************************
	 * CLASS DEFINITION
	 ******************************************/
	public static class TableInformation {
		@Getter final String name, prefix;
		final Class<? extends Entity> entity;
		@Setter Class<? extends RowMapper<?>> rowMapper;
		public TableInformation(Class<? extends Entity> entity) {
			Table annotation = entity.getAnnotation(Table.class);
			this.entity = entity;
			this.name = annotation.name();
			this.prefix = annotation.prefix();
		}
	}
}
