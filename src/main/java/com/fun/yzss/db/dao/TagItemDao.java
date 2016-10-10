package com.fun.yzss.db.dao;

import com.fun.yzss.db.entity.TagItemDo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by fanqq on 2016/10/10.
 */
@Service("tagItemDao")
public class TagItemDao {
    public TagItemDo[] findAllByType(String type) {
        return new TagItemDo[0];
    }

    public TagItemDo[] findAllByType(String tagName, String type) {
        return new TagItemDo[0];
    }

    public TagItemDo[] findByTagsAndType(Long[] tagIds, String type) {
        return new TagItemDo[0];
    }

    public List<TagItemDo> findByItemAndType(Long itemId, String type) {
        return null;
    }
}
