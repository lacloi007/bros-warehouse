package tpv.bros.common;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;

import tpv.core.annotation.Table;
import tpv.core.table.Entity;

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
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static class TableInformation {
		final String name;
		final String prefix;
		final Class<? extends Entity> entity;
		public TableInformation(Class<? extends Entity> entity) {
			Table annotation = entity.getAnnotation(Table.class);
			this.entity = entity;
			this.name = annotation.name();
			this.prefix = annotation.prefix();
		}
	}
}
