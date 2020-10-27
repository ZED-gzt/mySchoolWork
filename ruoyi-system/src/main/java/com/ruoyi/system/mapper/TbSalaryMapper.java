package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TbSalary;

/**
 * 工资Mapper接口
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
public interface TbSalaryMapper 
{
    /**
     * 查询工资
     * 
     * @param id 工资ID
     * @return 工资
     */
    public TbSalary selectTbSalaryById(Long id);

    /**
     * 查询工资列表
     * 
     * @param tbSalary 工资
     * @return 工资集合
     */
    public List<TbSalary> selectTbSalaryList(TbSalary tbSalary);

    /**
     * 新增工资
     * 
     * @param tbSalary 工资
     * @return 结果
     */
    public int insertTbSalary(TbSalary tbSalary);

    /**
     * 修改工资
     * 
     * @param tbSalary 工资
     * @return 结果
     */
    public int updateTbSalary(TbSalary tbSalary);

    /**
     * 删除工资
     * 
     * @param id 工资ID
     * @return 结果
     */
    public int deleteTbSalaryById(Long id);

    /**
     * 批量删除工资
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbSalaryByIds(Long[] ids);
}
