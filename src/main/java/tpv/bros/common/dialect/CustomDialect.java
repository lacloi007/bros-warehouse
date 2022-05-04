package tpv.bros.common.dialect;

import java.util.Collections;
import java.util.Set;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

import tpv.bros.common.utils.CustomThymeleafUtils;

public class CustomDialect implements IExpressionObjectDialect {
	static final String name = "utils";

	@Override
	public IExpressionObjectFactory getExpressionObjectFactory() {
		return new IExpressionObjectFactory() {
			@Override
			public Set<String> getAllExpressionObjectNames() {
				return Collections.singleton(name);
			}

			@Override
			public Object buildObject(IExpressionContext context, String expressionObjectName) {
				if (name.equals(expressionObjectName))
					return new CustomThymeleafUtils();
				return null;
			}

			@Override
			public boolean isCacheable(String expressionObjectName) {
				return false;
			}
		};
	}

	@Override
	public String getName() { return "CustomDialect"; }
}
