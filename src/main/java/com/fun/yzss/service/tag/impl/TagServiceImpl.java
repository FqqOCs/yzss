package com.fun.yzss.service.tag.impl;

import com.fun.yzss.db.dao.TagDao;
import com.fun.yzss.db.dao.TagItemDao;
import com.fun.yzss.db.entity.TagDo;
import com.fun.yzss.db.entity.TagItemDo;
import com.fun.yzss.service.tag.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fanqq on 2016/10/10.
 */
@Service("tagService")
public class TagServiceImpl implements TagService {
    @Resource
    private TagDao tagDao;
    @Resource
    private TagItemDao tagItemDao;


    @Override
    public void tagging(String tagName, String type, Long targetId) throws Exception {

    }

    @Override
    public void tagging(String tagName, String type, Long[] targetIds) throws Exception {

    }

    @Override
    public void untagging(String tagName, String type, Long targetId) throws Exception {

    }

    @Override
    public void untagging(String tagName, String type, Long[] targetIds) throws Exception {

    }

    @Override
    public Set<Long> queryByType(String type) throws Exception {
        Set<Long> result = new HashSet<>();
        for (TagItemDo d : tagItemDao.findAllByType(type)) {
            result.add(d.getTagId());
        }
        return result;
    }

    @Override
    public Set<Long> queryByTypeAndTag(String tagName, String type) throws Exception {
        Set<Long> result = new HashSet<>();
        for (TagItemDo d : tagItemDao.findAllByType(tagName, type)) {
            result.add(d.getTagId());
        }
        return result;
    }

    @Override
    public Set<Long> unionQuery(List<String> tagNames, String type) throws Exception {
        List<TagDo> tags = tagDao.findAllByNames(tagNames.toArray(new String[tagNames.size()]));
        if (tags.size() == 0) return new HashSet<>();

        Long[] tagIds = new Long[tags.size()];
        for (int i = 0; i < tagIds.length; i++) {
            tagIds[i] = tags.get(i).getId();
        }

        Set<Long> result = new HashSet<>();
        for (TagItemDo d : tagItemDao.findByTagsAndType(tagIds, type)) {
            result.add(d.getTargetId());
        }
        return result;
    }

    @Override
    public Set<Long> joinQuery(List<String> tagNames, String type) throws Exception {
        List<TagDo> tags = tagDao.findAllByNames(tagNames.toArray(new String[tagNames.size()]));
        if (tags.size() == 0) return new HashSet<>();

        Long[] tagIds = new Long[tags.size()];
        for (int i = 0; i < tagIds.length; i++) {
            tagIds[i] = tags.get(i).getId();
        }
        if (tagIds.length < tagNames.size()) return new HashSet<>();

        int joinedValue = tagIds.length;
        Map<Long, Counter> marker = new HashMap<>();
        for (TagItemDo d : tagItemDao.findByTagsAndType(tagIds, type)) {
            Counter m = marker.get(d.getTargetId());
            if (m == null) {
                marker.put(d.getTargetId(), new Counter());
            } else {
                m.incr();
            }
        }

        Set<Long> result = new HashSet<>();
        for (Map.Entry<Long, Counter> e : marker.entrySet()) {
            if (e.getValue().get() == joinedValue) result.add(e.getKey());
        }
        return result;
    }

    @Override
    public Set<String> getTags(Long targetId) throws Exception {
        Set<String> result = new HashSet<>();
        for (TagDo d : tagDao.findAllByIds(new Long[]{targetId})) {
            result.add(d.getName());
        }
        return result;
    }

    @Override
    public Set<String> getTags(Long[] targetIds) throws Exception {
        Set<String> result = new HashSet<>();
        for (TagDo d : tagDao.findAllByIds(targetIds)) {
            result.add(d.getName());
        }
        return result;
    }

    @Override
    public Set<String> getTags(String type, Long itemId) throws Exception {
        List<TagItemDo> list = tagItemDao.findByItemAndType(itemId, type);
        List<String> result = new ArrayList<>();
        if (list.size() == 0)
            return result;
        Long[] tagIds = new Long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tagIds[i] = list.get(i).getTagId();
        }
        for (TagDo tagDo : tagDao.findAllByIds(tagIds, TagEntity.READSET_FULL)) {
            result.add(tagDo.getName());
        }
        return result;
    }

    @Override
    public Map<Long, List<String>> getTags(String type, Long[] itemIds) throws Exception {
        return null;
    }

    private class Counter {
        int count = 1;

        public void incr() {
            count++;
        }

        public int get() {
            return count;
        }
    }
}


