package io.katharsis.validation.meta;

import javax.validation.constraints.NotNull;

import io.katharsis.meta.model.MetaAttribute;
import io.katharsis.meta.model.MetaElement;
import io.katharsis.meta.provider.MetaProviderBase;

/**
 * Provides an integration into katharsis-meta. Currently features:
 * 
 * <ul>
 * 	<li>
 * 		disable nullability for attributes annotated with {@link javax.validation.constraints.NotNull}.
 *  <li>
 * </ul>
 * 
 * More features to follow (constraints, etc.).
 */
public class ValidationMetaProvider extends MetaProviderBase {

	public void onInitialized(MetaElement element) {
		if (element instanceof MetaAttribute) {
			MetaAttribute attr = (MetaAttribute) element;

			NotNull notNull = attr.getAnnotation(NotNull.class);
			if (notNull != null) {
				attr.setNullable(false);
			}
		}
	}
}
