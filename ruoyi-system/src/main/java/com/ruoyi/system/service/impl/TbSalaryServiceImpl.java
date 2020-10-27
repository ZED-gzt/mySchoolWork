package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TbSalaryMapper;
import com.ruoyi.system.domain.TbSalary;
import com.ruoyi.system.service.ITbSalaryService;

/**
 * 工资Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Service
public class TbSalaryServiceImpl implements ITbSalaryService 
{
    @Autowired
    private TbSalaryMapper tbSalaryMapper;

    /**
     * 查询工资
     * 
     * @param id 工资ID
     * @return 工资
     */
    @Override
    public TbSalary selectTbSalaryById(Long id)
    {
        return tbSalaryMapper.selectTbSalaryById(id);
    }

    /**
     * 查询工资列表
     * 
     * @param tbSalary 工资
     * @return 工资
     */
    @Override
    public List<TbSalary> selectTbSalaryList(TbSalary tbSalary)
    {
        return tbSalaryMapper.selectTbSalaryList(tbSalary);
    }

    /**
     * 新增工资
     * 
     * @param tbSalary 工资
     * @return 结果
     */
    @Override
    public int insertTbSalary(TbSalary tbSalary)
    {
        return tbSalaryMapper.insertTbSalary(tbSalary);
    }

    /**
     * 修改工资
     * 
     * @param tbSalary 工资
     * @return 结果
     */
    @Override
    public int updateTbSalary(TbSalary tbSalary)
    {
        return tbSalaryMapper.updateTbSalary(tbSalary);
    }

    /**
     * 批量删除工资
     * 
     * @param ids 需要删除的工资ID
     * @return 结果
     */
    @Override
    public int deleteTbSalaryByIds(Long[] ids)
    {
        return tbSalaryMapper.deleteTbSalaryByIds(ids);
    }

    /**
     * 删除工资信息
     * 
     * @param id 工资ID
     * @return 结果
     */
    @Override
    public int deleteTbSalaryById(Long id)
    {
        return tbSalaryMapper.deleteTbSalaryById(id);
    }
}
