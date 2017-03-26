package io.katharsis.jpa.internal.query;

import java.util.Set;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Subgraph;

import io.katharsis.meta.model.MetaAttributePath;

public class EntityGraphBuilderImpl implements EntityGraphBuilder {

	@Override
	public <T> void build(EntityManager em, Query criteriaQuery, Class<T> entityClass,
			Set<MetaAttributePath> fetchPaths) {
		EntityGraph<T> graph = em.createEntityGraph(entityClass);
		for (MetaAttributePath fetchPath : fetchPaths) {
			applyFetchPaths(graph, fetchPath);
		}
		criteriaQuery.setHint("javax.persistence.fetchgraph", graph);
	}

	private <T> Subgraph<Object> applyFetchPaths(EntityGraph<T> graph, MetaAttributePath fetchPath) {
		if (fetchPath.length() >= 2) {
			// ensure parent is fetched
			MetaAttributePath parentPath = fetchPath.subPath(0, fetchPath.length() - 1);
			Subgraph<Object> parentGraph = applyFetchPaths(graph, parentPath);
			return parentGraph.addSubgraph(fetchPath.toString());
		} else {
			return graph.addSubgraph(fetchPath.toString());
		}
	}
}
