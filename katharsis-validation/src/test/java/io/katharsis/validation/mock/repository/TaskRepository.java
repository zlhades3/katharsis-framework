package io.katharsis.validation.mock.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import io.katharsis.errorhandling.exception.ResourceNotFoundException;
import io.katharsis.legacy.queryParams.QueryParams;
import io.katharsis.legacy.repository.ResourceRepository;
import io.katharsis.validation.mock.models.Task;

public class TaskRepository implements ResourceRepository<Task, Long> {

	public static final ConcurrentHashMap<Long, Task> map = new ConcurrentHashMap<>();

	@Override
	public <S extends Task> S save(S entity) {
		if (entity.getId() == null) {
			entity.setId((long) (map.size() + 1));
		}
		map.put(entity.getId(), entity);

		return entity;
	}

	@Override
	public Task findOne(Long aLong, QueryParams queryParams) {
		Task task = map.get(aLong);
		if (task == null) {
			throw new ResourceNotFoundException("");
		}
		return task;
	}

	@Override
	public Iterable<Task> findAll(QueryParams queryParams) {
		return map.values();
	}

	@Override
	public Iterable<Task> findAll(Iterable<Long> ids, QueryParams queryParams) {
		List<Task> values = new LinkedList<>();
		for (Task value : map.values()) {
			if (contains(value, ids)) {
				values.add(value);
			}
		}
		return values;
	}

	private boolean contains(Task value, Iterable<Long> ids) {
		for (Long id : ids) {
			if (value.getId().equals(id)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void delete(Long aLong) {
		map.remove(aLong);
	}
}
