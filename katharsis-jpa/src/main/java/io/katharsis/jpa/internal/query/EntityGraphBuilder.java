package io.katharsis.jpa.internal.query;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import io.katharsis.meta.model.MetaAttributePath;

public interface EntityGraphBuilder {

	public <T> void build(EntityManager em, Query criteriaQuery, Class<T> entityClass,
			Set<MetaAttributePath> fetchPaths);

}
