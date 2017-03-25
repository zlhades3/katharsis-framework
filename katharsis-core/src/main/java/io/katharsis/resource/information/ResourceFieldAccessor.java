package io.katharsis.resource.information;

import io.katharsis.core.internal.resource.ReflectionFieldAccessor;

/**
 * Provides access to a field of a resource. See {@link ReflectionFieldAccessor}
 * for a default implementation.
 * 
 * @author Remo
 */
public interface ResourceFieldAccessor {

	public Object getValue(Object resource);

	public void setValue(Object resource, Object fieldValue);

}
