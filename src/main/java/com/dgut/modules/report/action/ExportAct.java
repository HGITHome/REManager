package com.dgut.modules.report.action;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dgut.modules.border.entity.dto.BusinessOrderDto;
import com.dgut.modules.border.entity.support.TransactionStateEnum;
import com.dgut.modules.border.service.BusinessOrderMng;
import com.dgut.modules.common.utils.DateUtils;
import com.dgut.modules.common.utils.ExportExcel;
import com.dgut.modules.common.web.PageableWrap;
import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.order.entity.support.SalesTypeEnum;
import com.dgut.modules.realEstate.entity.RealEstate;
import com.dgut.modules.realEstate.entity.dto.RealEstateDto;
import com.dgut.modules.realEstate.service.RealEstateMng;
import com.dgut.modules.report.service.ExportMng;
import com.dgut.modules.room.entity.dto.RoomDto;
import com.dgut.modules.room.entity.dto.RoomWebDto;
import com.dgut.modules.room.service.RoomMng;
@Controller
@RequestMapping("export")
public class ExportAct {

	@RequestMapping("o_export.do")
    public void ExportEscel(HttpServletRequest request,HttpServletResponse response,RoomDto dto,PageableWrap pageable) throws UnsupportedEncodingException, ParseException{
		String salesType = request.getParameter("salesType");
		if(!StringUtils.isBlank(salesType)) {
			dto.setSalesType(SalesTypeEnum.valueOf(salesType));
		}
		List<RoomWebDto> webList = esportMng.getPage(RoomDto.getWhereClause(dto), pageable.getPageable());
		
		String fileName = DateUtils.getSystemDate(); 
		String filename= new String(fileName+".xls");//中文文件名必须使用此句话
        response.setContentType("application/octet-stream");
        response.setContentType("application/OCTET-STREAM;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename="+filename );
        String[] headers = {"id","房号","楼层","栋","面积","房间","厅","卫生间","层数","有无停车场","有无庭院","业主名","楼盘名","租售类型"};  //表格的标题栏
       
        //WorkloadExport 为将要导出的类，也就是表格的一行记录，里面的所有字段都不能为空，必须生成set get方法
        //导出列顺序和类中成员顺序一致
        try {
            ExportExcel<RoomWebDto> ex = new ExportExcel<RoomWebDto>();  //构造导出类

            OutputStream  out = new BufferedOutputStream(response.getOutputStream());
            String  title = "Sheet";  //title需要自己指定 比如写Sheet
            ex.exportExcel(title,headers, webList, out);  //title是excel表中底部显示的表格名，如Sheet
            out.close();
            System.out.println("excel导出成功！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	@RequestMapping("o_dealExport.do")
    public void DealExportEscel(HttpServletRequest request,HttpServletResponse response,PageableWrap pageable) throws UnsupportedEncodingException, ParseException{
		BusinessOrderDto dto = new BusinessOrderDto();
    	if(!StringUtils.isBlank(request.getParameter("salesType"))) {
			dto.setSalesType(SalesTypeEnum.valueOf(request.getParameter("salesType")));
		}
    	dto.setTransactionState(TransactionStateEnum.SOLD);
    	WebPage<BusinessOrderDto, Long> webPage = bOrderMng.findAll(BusinessOrderDto.getWhereClause(dto), pageable.getPageable());
		List<BusinessOrderDto> webList = webPage.getContentList();
		
		String fileName = DateUtils.getSystemDate(); 
		String filename= new String(fileName+".xls");//中文文件名必须使用此句话
        response.setContentType("application/octet-stream");
        response.setContentType("application/OCTET-STREAM;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename="+filename );
        String[] headers = {"id","房号","租售","员工名","交易状态","优先级","交易金额","用户名","中介费","创建时间","完成时间"};  //表格的标题栏
       
        //WorkloadExport 为将要导出的类，也就是表格的一行记录，里面的所有字段都不能为空，必须生成set get方法
        //导出列顺序和类中成员顺序一致
        try {
            ExportExcel<BusinessOrderDto> ex = new ExportExcel<BusinessOrderDto>();  //构造导出类

            OutputStream  out = new BufferedOutputStream(response.getOutputStream());
            String  title = "Sheet";  //title需要自己指定 比如写Sheet
            ex.exportExcel(title,headers, webList, out);  //title是excel表中底部显示的表格名，如Sheet
            out.close();
            System.out.println("excel导出成功！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	@Autowired
	private RealEstateMng realEstateMng;
	
	@Autowired
	private BusinessOrderMng bOrderMng;
	
	@Autowired
	private RoomMng roomMng;
	
	@Autowired
	private ExportMng esportMng;
}
