package com.ruoyi.web.controller.system;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.TbSalary;
import com.ruoyi.system.service.ITbSalaryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工资Controller
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@RestController
@RequestMapping("/system/salary")
public class TbSalaryController extends BaseController
{
    @Autowired
    private ITbSalaryService tbSalaryService;

    /**
     * 查询工资列表
     */
    @PreAuthorize("@ss.hasPermi('system:salary:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbSalary tbSalary)
    {
        startPage();
        List<TbSalary> list = tbSalaryService.selectTbSalaryList(tbSalary);
        return getDataTable(list);
    }

    /**
     * 导出工资列表
     */
    @PreAuthorize("@ss.hasPermi('system:salary:export')")
    @Log(title = "工资", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TbSalary tbSalary)
    {
        List<TbSalary> list = tbSalaryService.selectTbSalaryList(tbSalary);
        ExcelUtil<TbSalary> util = new ExcelUtil<TbSalary>(TbSalary.class);
        return util.exportExcel(list, "salary");
    }

    /**
     * 获取工资详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:salary:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tbSalaryService.selectTbSalaryById(id));
    }

    /**
     * 新增工资
     */
    @PreAuthorize("@ss.hasPermi('system:salary:add')")
    @Log(title = "工资", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbSalary tbSalary)
    {
        return toAjax(tbSalaryService.insertTbSalary(tbSalary));
    }

    /**
     * 修改工资
     */
    @PreAuthorize("@ss.hasPermi('system:salary:edit')")
    @Log(title = "工资", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbSalary tbSalary)
    {
        return toAjax(tbSalaryService.updateTbSalary(tbSalary));
    }

    /**
     * 删除工资
     */
    @PreAuthorize("@ss.hasPermi('system:salary:remove')")
    @Log(title = "工资", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbSalaryService.deleteTbSalaryByIds(ids));
    }
}
