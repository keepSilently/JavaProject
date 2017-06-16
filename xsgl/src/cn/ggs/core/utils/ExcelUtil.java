package cn.ggs.core.utils;

import cn.ggs.core.constant.Constant;
import cn.ggs.core.entity.Student;
import cn.ggs.core.entity.User;

import cn.ggs.core.service.StudentService;
import cn.ggs.core.service.UserService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.FileInputStream;

import java.util.Date;
import java.util.List;

/**
 * Created by silent on 2017-05-29/029.
 */
@SuppressWarnings("deprecation")
public class ExcelUtil {

    /**
     * 导出学生的所有列表到excel
     * @param stuList 学生列表
     * @param outputStream 输出流
     */
    public static void exportStudentExcel(List<Student> stuList, ServletOutputStream outputStream) {
        try {
            //1、创建工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            //1.1、创建合并单元格对象
            CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 6);//起始行号，结束行号，起始列号，结束列号

            //1.2、头标题样式
            HSSFCellStyle style1 = createCellStyle(workbook, (short)18);

            //1.3、列标题样式
            HSSFCellStyle style2 = createCellStyle(workbook, (short)16);
            HSSFCellStyle cellMiddleStyle = createCellMiddleStyle(workbook);



            //2、创建工作表
            HSSFSheet sheet = workbook.createSheet("学生列表");

            //2.1、加载合并单元格对象
            sheet.addMergedRegion(cellRangeAddress);
            //设置默认列宽
            sheet.setDefaultColumnWidth(25);
            sheet.setDefaultRowHeightInPoints(20);

            //3、创建行
            //3.1、创建头标题行；并且设置头标题
            HSSFRow row1 = sheet.createRow(0);
            HSSFCell cell1 = row1.createCell(0);
            //加载单元格样式
            cell1.setCellStyle(style1);
            cell1.setCellValue("学生列表");

            //3.2、创建列标题行；并且设置列标题
            HSSFRow row2 = sheet.createRow(1);
            String[] titles = {"学号","姓名", "性别","联系电话", "生日", "宿舍楼","宿舍号"};
            for(int i = 0; i < titles.length; i++){
                HSSFCell cell2 = row2.createCell(i);
                //加载单元格样式
                cell2.setCellStyle(style2);
                cell2.setCellValue(titles[i]);
            }


            //4、操作单元格；将用户列表写入excel
            if(stuList != null){
                for(int j = 0; j < stuList.size(); j++){
                    HSSFRow row = sheet.createRow(j+2);
                    HSSFCell cell11 = row.createCell(0);
                    cell11.setCellValue(stuList.get(j).getSchoolId());
                    HSSFCell cell12 = row.createCell(1);
                    cell12.setCellValue(stuList.get(j).getName());
                    HSSFCell cell13 = row.createCell(2);
                    if(stuList.get(j).getGender()!=null){
                        cell13.setCellValue(stuList.get(j).getGender()?"男":"女");
                    }
                    HSSFCell cell14 = row.createCell(3);
                    HSSFCell cell15 = row.createCell(4);
                    HSSFCell cell16 = row.createCell(5);
                    HSSFCell cell17 = row.createCell(6);
                    cell17.setCellValue(stuList.get(j).getDormitoryNum());
                    cell16.setCellValue(stuList.get(j).getDormitory());
                    if(stuList.get(j).getBirthday()!=null){
                        cell15.setCellValue(stuList.get(j).getBirthday());
                    }

                    cell14.setCellValue(stuList.get(j).getMobile());

                    cell11.setCellStyle(cellMiddleStyle);
                    cell12.setCellStyle(cellMiddleStyle);
                    cell13.setCellStyle(cellMiddleStyle);
                    cell14.setCellStyle(cellMiddleStyle);
                    cell15.setCellStyle(cellMiddleStyle);
                    cell16.setCellStyle(cellMiddleStyle);
                    cell17.setCellStyle(cellMiddleStyle);


                }
            }
            //5、输出
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exportUserExcel(List<User> userList, ServletOutputStream outputStream) {
        try {
            //1、创建工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            CellRangeAddress cellRangeAddress=null;

            //1.1、创建合并单元格对象
            if(userList==null){
                cellRangeAddress = new CellRangeAddress(0, 0, 0, 1);//起始行号，结束行号，起始列号，结束列号

            }else {
                cellRangeAddress = new CellRangeAddress(0, 0, 0, 2);
            }


            //1.2、头标题样式
            HSSFCellStyle style1 = createCellStyle(workbook, (short)18);

            //1.3、列标题样式
            HSSFCellStyle style2 = createCellStyle(workbook, (short)16);
            HSSFCellStyle cellMiddleStyle = createCellMiddleStyle(workbook);



            //2、创建工作表
            HSSFSheet sheet = workbook.createSheet("用户列表 ");

            //2.1、加载合并单元格对象
            sheet.addMergedRegion(cellRangeAddress);
            //设置默认列宽
            sheet.setDefaultColumnWidth(25);
            sheet.setDefaultRowHeightInPoints(20);

            //3、创建行
            //3.1、创建头标题行；并且设置头标题
            HSSFRow row1 = sheet.createRow(0);
            HSSFCell cell1 = row1.createCell(0);
            //加载单元格样式
            cell1.setCellStyle(style1);
            cell1.setCellValue("用户列表");

            //3.2、创建列标题行；并且设置列标题
            HSSFRow row2 = sheet.createRow(1);
            String[] titles = {"用户名","密码","权限"};
            for(int i = 0; i < titles.length; i++){
                if(userList==null&&i==2){
                    continue;
                }
                HSSFCell cell2 = row2.createCell(i);
                //加载单元格样式
                cell2.setCellStyle(style2);
                cell2.setCellValue(titles[i]);
            }


            //4、操作单元格；将用户列表写入excel
            if(userList != null){
                for(int j = 0; j < userList.size(); j++){
                    HSSFRow row = sheet.createRow(j+2);
                    HSSFCell cell11 = row.createCell(0);
                    cell11.setCellValue(userList.get(j).getName());
                    HSSFCell cell12 = row.createCell(1);
                    cell12.setCellValue(userList.get(j).getPassword());
                    HSSFCell cell13 = row.createCell(2);
                    cell13.setCellValue(Constant.PRIVILEGE_MAP.get(userList.get(j).getPermission()));
                    cell11.setCellStyle(cellMiddleStyle);
                    cell12.setCellStyle(cellMiddleStyle);
                    cell13.setCellStyle(cellMiddleStyle);
                }
            }
            //5、输出
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建单元格样式
     * @param workbook 工作簿
     * @param fontSize 字体大小
     * @return 单元格样式
     */
    private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontSize) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        //创建字体
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗字体
        font.setFontHeightInPoints(fontSize);
        //加载字体
        style.setFont(font);
        return style;
    }





    /***
     *
     * @param workbook
     * @return  设置居中
     */
    private static HSSFCellStyle createCellMiddleStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.TEAL.index);//字体颜色
        font.setFontHeightInPoints((short) 14);//字体大小
        font.setBoldweight(HSSFFont.U_NONE);
        //加载字体
        style.setFont(font);


        //设置日期
        CreationHelper creationHelper = workbook.getCreationHelper();
        style.setDataFormat(
                creationHelper.createDataFormat().getFormat("yyyy-MM-dd")
        );

        return style;
    }

    public static void readStudent(File stuExcel, String userExcelFileName, StudentService service){
        try {
            FileInputStream fileInputStream = new FileInputStream(stuExcel);
            boolean is03Excel = userExcelFileName.matches("^.+\\.(?i)(xls)$");
            //1、读取工作簿
            Workbook workbook = is03Excel ? new HSSFWorkbook(fileInputStream):new XSSFWorkbook(fileInputStream);
            //2、读取工作表
            Sheet sheet = workbook.getSheetAt(0);
            //3、读取行
            if(sheet.getPhysicalNumberOfRows() > 2){

                for(int k = 2; k < sheet.getPhysicalNumberOfRows(); k++){
                    //4、读取单元格
                    Row row = sheet.getRow(k);
                    Student stu=new Student();
                    Cell cell0 = row.getCell(0);//学号
                    Cell cell1 = row.getCell(1);//姓名
                    Cell cell2 = row.getCell(2);//性别
                    Cell cell3 = row.getCell(3);//联系电话
                    Cell cell4 = row.getCell(4);//生日
                    Cell cell5 = row.getCell(5);//宿舍楼
                    Cell cell6 = row.getCell(6);//宿舍号


                    String  schoolId=cell0.getStringCellValue();

                    if(StringUtils.isNoneBlank(schoolId)){
                        schoolId=  schoolId.replaceAll("\\W", "");
                        stu.setSchoolId(schoolId);
                    }

                    stu.setName(cell1.getStringCellValue());
                    String gender=cell2.getStringCellValue();
                    if(StringUtils.isNoneBlank(gender)){
                        if(gender.equals("男")){
                            stu.setGender(true);
                        }else {
                            stu.setGender(false);
                        }
                    }

                    String  moblie=cell3.getStringCellValue();
                           // ’201502100225
                    //替换’
                    if(StringUtils.isNoneBlank(moblie)){
                        moblie=  moblie.replaceAll("\\W", "");
                        stu.setMobile(moblie);
                    }

                    try{
                        Date dateCellValue = cell4.getDateCellValue();
                        stu.setBirthday(dateCellValue);
                    }
                    catch (Exception e){
                        System.out.println("日期读取失败:"+cell4.getStringCellValue()+e.getMessage());
                    }
                    stu.setDormitory(cell5.getStringCellValue());
                    stu.setDormitoryNum(cell6.getStringCellValue());
                    service.save(stu);

                }
            }
            workbook.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readUser(File userExcel, String userExcelFileName, UserService service){
        try {
            FileInputStream fileInputStream = new FileInputStream(userExcel);
            boolean is03Excel = userExcelFileName.matches("^.+\\.(?i)(xls)$");
            //1、读取工作簿
            Workbook workbook = is03Excel ? new HSSFWorkbook(fileInputStream):new XSSFWorkbook(fileInputStream);
            //2、读取工作表
            Sheet sheet = workbook.getSheetAt(0);
            //3、读取行
            if(sheet.getPhysicalNumberOfRows() > 2){
                User user = null;
                for(int k = 2; k < sheet.getPhysicalNumberOfRows(); k++){
                    //4、读取单元格
                    Row row = sheet.getRow(k);
                    user=new User();
                    Cell cell0 = row.getCell(0);//用户名
                    Cell cell1 = row.getCell(1);//密码
                    user.setName(cell0.getStringCellValue());
                    user.setPassword(cell1.getStringCellValue());
                    user.setPermission(Constant.PRIVILEGE_USER);
                    service.save(user);
                }
            }
            workbook.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
