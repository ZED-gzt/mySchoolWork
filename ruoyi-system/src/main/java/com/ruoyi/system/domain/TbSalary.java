package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工资对象 tb_salary
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
public class TbSalary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键Id */
    private Long id;

    /** 员工 */
    @Excel(name = "员工")
    private String userName;

    /** 出勤天数 */
    @Excel(name = "出勤天数")
    private BigDecimal attendDay;

    /** 考核系数 */
    @Excel(name = "考核系数")
    private Long assessNum;

    /** 基本工资 */
    @Excel(name = "基本工资")
    private BigDecimal basicSalary;

    /** 绩效工资 */
    @Excel(name = "绩效工资")
    private BigDecimal meritSalary;

    /** 应发工资 */
    @Excel(name = "应发工资")
    private BigDecimal oughtTotalSalary;

    /** 应发基本工资 */
    @Excel(name = "应发基本工资")
    private BigDecimal oughtBasicSalary;

    /** 应发绩效工资 */
    @Excel(name = "应发绩效工资")
    private BigDecimal oughtMeritSalary;

    /** 补助 */
    @Excel(name = "补助")
    private BigDecimal grants;

    /** 其他补助 */
    @Excel(name = "其他补助")
    private BigDecimal otherGrants;

    /** 应扣工资合计 */
    @Excel(name = "应扣工资合计")
    private BigDecimal oughtdeductSalary;

    /** 考勤扣款 */
    @Excel(name = "考勤扣款")
    private BigDecimal attendPay;

    /** 个人养老 */
    @Excel(name = "个人养老")
    private BigDecimal personAgePay;

    /** 公司承担养老 */
    @Excel(name = "公司承担养老")
    private BigDecimal companyAgePay;

    /** 个人医保 */
    @Excel(name = "个人医保")
    private BigDecimal personMedialPay;

    /** 公司承担医保 */
    @Excel(name = "公司承担医保")
    private BigDecimal companyMedialPay;

    /** 个人失业险 */
    @Excel(name = "个人失业险")
    private BigDecimal personUnemployPay;

    /** 公司承担失业险 */
    @Excel(name = "公司承担失业险")
    private BigDecimal companyUnemployPay;

    /** 个人公积金 */
    @Excel(name = "个人公积金")
    private BigDecimal personReserveFund;

    /** 公司承担公积金 */
    @Excel(name = "公司承担公积金")
    private BigDecimal companyReserveFund;

    /** 餐费 */
    @Excel(name = "餐费")
    private BigDecimal mealPay;

    /** 个人税 */
    @Excel(name = "个人税")
    private BigDecimal personTax;

    /** 实发工资 */
    @Excel(name = "实发工资")
    private BigDecimal realTotalSalary;

    /** 工资年 */
    @Excel(name = "工资年")
    private Long salaryYear;

    /** 工资月 */
    @Excel(name = "工资月")
    private Long salaryMonth;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setAttendDay(BigDecimal attendDay) 
    {
        this.attendDay = attendDay;
    }

    public BigDecimal getAttendDay() 
    {
        return attendDay;
    }
    public void setAssessNum(Long assessNum) 
    {
        this.assessNum = assessNum;
    }

    public Long getAssessNum() 
    {
        return assessNum;
    }
    public void setBasicSalary(BigDecimal basicSalary) 
    {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getBasicSalary() 
    {
        return basicSalary;
    }
    public void setMeritSalary(BigDecimal meritSalary) 
    {
        this.meritSalary = meritSalary;
    }

    public BigDecimal getMeritSalary() 
    {
        return meritSalary;
    }
    public void setOughtTotalSalary(BigDecimal oughtTotalSalary) 
    {
        this.oughtTotalSalary = oughtTotalSalary;
    }

    public BigDecimal getOughtTotalSalary() 
    {
        return oughtTotalSalary;
    }
    public void setOughtBasicSalary(BigDecimal oughtBasicSalary) 
    {
        this.oughtBasicSalary = oughtBasicSalary;
    }

    public BigDecimal getOughtBasicSalary() 
    {
        return oughtBasicSalary;
    }
    public void setOughtMeritSalary(BigDecimal oughtMeritSalary) 
    {
        this.oughtMeritSalary = oughtMeritSalary;
    }

    public BigDecimal getOughtMeritSalary() 
    {
        return oughtMeritSalary;
    }
    public void setGrants(BigDecimal grants) 
    {
        this.grants = grants;
    }

    public BigDecimal getGrants() 
    {
        return grants;
    }
    public void setOtherGrants(BigDecimal otherGrants) 
    {
        this.otherGrants = otherGrants;
    }

    public BigDecimal getOtherGrants() 
    {
        return otherGrants;
    }
    public void setOughtdeductSalary(BigDecimal oughtdeductSalary) 
    {
        this.oughtdeductSalary = oughtdeductSalary;
    }

    public BigDecimal getOughtdeductSalary() 
    {
        return oughtdeductSalary;
    }
    public void setAttendPay(BigDecimal attendPay) 
    {
        this.attendPay = attendPay;
    }

    public BigDecimal getAttendPay() 
    {
        return attendPay;
    }
    public void setPersonAgePay(BigDecimal personAgePay) 
    {
        this.personAgePay = personAgePay;
    }

    public BigDecimal getPersonAgePay() 
    {
        return personAgePay;
    }
    public void setCompanyAgePay(BigDecimal companyAgePay) 
    {
        this.companyAgePay = companyAgePay;
    }

    public BigDecimal getCompanyAgePay() 
    {
        return companyAgePay;
    }
    public void setPersonMedialPay(BigDecimal personMedialPay) 
    {
        this.personMedialPay = personMedialPay;
    }

    public BigDecimal getPersonMedialPay() 
    {
        return personMedialPay;
    }
    public void setCompanyMedialPay(BigDecimal companyMedialPay) 
    {
        this.companyMedialPay = companyMedialPay;
    }

    public BigDecimal getCompanyMedialPay() 
    {
        return companyMedialPay;
    }
    public void setPersonUnemployPay(BigDecimal personUnemployPay) 
    {
        this.personUnemployPay = personUnemployPay;
    }

    public BigDecimal getPersonUnemployPay() 
    {
        return personUnemployPay;
    }
    public void setCompanyUnemployPay(BigDecimal companyUnemployPay) 
    {
        this.companyUnemployPay = companyUnemployPay;
    }

    public BigDecimal getCompanyUnemployPay() 
    {
        return companyUnemployPay;
    }
    public void setPersonReserveFund(BigDecimal personReserveFund) 
    {
        this.personReserveFund = personReserveFund;
    }

    public BigDecimal getPersonReserveFund() 
    {
        return personReserveFund;
    }
    public void setCompanyReserveFund(BigDecimal companyReserveFund) 
    {
        this.companyReserveFund = companyReserveFund;
    }

    public BigDecimal getCompanyReserveFund() 
    {
        return companyReserveFund;
    }
    public void setMealPay(BigDecimal mealPay) 
    {
        this.mealPay = mealPay;
    }

    public BigDecimal getMealPay() 
    {
        return mealPay;
    }
    public void setPersonTax(BigDecimal personTax) 
    {
        this.personTax = personTax;
    }

    public BigDecimal getPersonTax() 
    {
        return personTax;
    }
    public void setRealTotalSalary(BigDecimal realTotalSalary) 
    {
        this.realTotalSalary = realTotalSalary;
    }

    public BigDecimal getRealTotalSalary() 
    {
        return realTotalSalary;
    }
    public void setSalaryYear(Long salaryYear) 
    {
        this.salaryYear = salaryYear;
    }

    public Long getSalaryYear() 
    {
        return salaryYear;
    }
    public void setSalaryMonth(Long salaryMonth) 
    {
        this.salaryMonth = salaryMonth;
    }

    public Long getSalaryMonth() 
    {
        return salaryMonth;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userName", getUserName())
            .append("attendDay", getAttendDay())
            .append("assessNum", getAssessNum())
            .append("basicSalary", getBasicSalary())
            .append("meritSalary", getMeritSalary())
            .append("oughtTotalSalary", getOughtTotalSalary())
            .append("oughtBasicSalary", getOughtBasicSalary())
            .append("oughtMeritSalary", getOughtMeritSalary())
            .append("grants", getGrants())
            .append("otherGrants", getOtherGrants())
            .append("oughtdeductSalary", getOughtdeductSalary())
            .append("attendPay", getAttendPay())
            .append("personAgePay", getPersonAgePay())
            .append("companyAgePay", getCompanyAgePay())
            .append("personMedialPay", getPersonMedialPay())
            .append("companyMedialPay", getCompanyMedialPay())
            .append("personUnemployPay", getPersonUnemployPay())
            .append("companyUnemployPay", getCompanyUnemployPay())
            .append("personReserveFund", getPersonReserveFund())
            .append("companyReserveFund", getCompanyReserveFund())
            .append("mealPay", getMealPay())
            .append("personTax", getPersonTax())
            .append("realTotalSalary", getRealTotalSalary())
            .append("salaryYear", getSalaryYear())
            .append("salaryMonth", getSalaryMonth())
            .append("createDate", getCreateDate())
            .append("remark", getRemark())
            .toString();
    }
}
