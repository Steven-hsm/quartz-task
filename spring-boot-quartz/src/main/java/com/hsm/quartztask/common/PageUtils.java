package com.hsm.quartztask.common;

import java.util.List;

/**
 * @author huangsenming
 * @Description: 分页工具
 * @date 2020/4/10 09:45
 */
public class PageUtils {

    /**
     * 将列表数据分页传输
     * @param list
     * @param queryVO
     * @param <T>
     * @return
     */
    public static <T> PageBO<T> transferToPage(List<T> list, QueryVO queryVO){
        PageBO page = new PageBO();
        page.setTotalCount(list.size());
        page.setStart((queryVO.getPage() - 1 ) * queryVO.getPerSize());
        int end = page.getStart() + queryVO.getPerSize();
        page.setEnd(end >= page.getTotalCount() ? page.getTotalCount() : end);
        page.setData(list.subList(page.getStart(),page.getEnd() ));
        return page;
    }
}
