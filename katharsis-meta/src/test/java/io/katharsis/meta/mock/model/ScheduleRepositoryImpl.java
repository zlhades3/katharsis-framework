package io.katharsis.meta.mock.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;

public class ScheduleRepositoryImpl extends ResourceRepositoryBase<Schedule, Long> implements ScheduleRepository {

	private static Map<Long, Schedule> schedules = new HashMap<>();

	private AtomicLong nextId = new AtomicLong(1000);

	public ScheduleRepositoryImpl() {
		super(Schedule.class);
	}

	@Override
	public String repositoryAction(String msg) {
		return "repository action: " + msg;
	}

	@Override
	public String resourceAction(long id, String msg) {
		Schedule schedule = findOne(id, new QuerySpec(Schedule.class));
		return "resource action: " + msg + "@" + schedule.getName();
	}

	@Override
	public ScheduleList findAll(QuerySpec querySpec) {
		ScheduleList list = new ScheduleList();
		list.addAll(querySpec.apply(schedules.values()));
		list.setLinks(new ScheduleListLinks());
		list.setMeta(new ScheduleListMeta());
		return list;
	}

	@Override
	public <S extends Schedule> S create(S entity) {
		if (entity.getId() == null) {
			entity.setId(nextId.incrementAndGet());
		}
		return save(entity);
	}

	@Override
	public <S extends Schedule> S save(S entity) {
		schedules.put(entity.getId(), entity);

		if (entity.getTasks() != null) {
			for (Task task : entity.getTasks()) {
				task.setSchedule(entity);
			}
		}

		return entity;
	}

	@Override
	public void delete(Long id) {
		schedules.remove(id);
	}

	public static void clear() {
		schedules.clear();
	}
}