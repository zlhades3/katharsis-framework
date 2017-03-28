package io.katharsis.meta.provider.resource;

import java.util.Arrays;
import java.util.Collection;

import io.katharsis.meta.internal.JsonObjectMetaProvider;
import io.katharsis.meta.internal.ResourceMetaProviderImpl;
import io.katharsis.meta.provider.MetaProvider;
import io.katharsis.meta.provider.MetaProviderBase;

public class ResourceMetaProvider extends MetaProviderBase {

	private boolean useResourceRegistry;

	public ResourceMetaProvider() {
		this(true);
	}

	public ResourceMetaProvider(boolean useResourceRegistry) {
		this.useResourceRegistry = useResourceRegistry;
	}

	@Override
	public Collection<MetaProvider> getDependencies() {
		return Arrays.asList((MetaProvider) new ResourceMetaProviderImpl(useResourceRegistry), new JsonObjectMetaProvider());
	}
}
