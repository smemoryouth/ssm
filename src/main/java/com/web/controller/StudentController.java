package com.web.controller;

import com.bean.Clazz;
import com.bean.Student;
import com.service.ClassService;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description：
 *
 * @author 阿劼
 * data 2019/1/20 11:04
 */
@Controller
public class StudentController {
    @Autowired
    ClassService cs;

    @Autowired
    StudentService ss;

    @RequestMapping(value = {"/login"},
            method=RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String name, String pwd, HttpSession session){
        Student stu = ss.queryStudent(name, pwd);
        System.out.println(stu);
        if(stu == null){
            return "redirect:/login";
        } else{
            session.setAttribute("student", stu);
            return "redirect:/main";
        }
    }

    @RequestMapping("/main")
    public String main(Model model, HttpServletRequest request){
        List<Student> stuList = ss.queryAllStudent();
        System.out.println("=============");
        for (Student s : stuList){
            System.out.println(s);
        }
        model.addAttribute("stuList", stuList);

        /*获取assets/file目录下的所有文件名字在页面进行显示，也可以封装在service层当中，此处为了简便，直接
         * 在web层实现*/
        File root = new File(request.getServletContext().getRealPath("/assets/file"));
        File[] files = root.listFiles();
        Map<String, String> fileMap = new HashMap<>();
        if (files != null) {
            for (File file : files) {
                try {
                    fileMap.put(file.getName(), URLEncoder.encode(file.getName(), "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        model.addAttribute("fileMap", fileMap);

        return "main";
    }

    @RequestMapping("/delete/uid/{uid}/cid/{cid}")
    public String delete(@PathVariable Integer uid, @PathVariable Integer cid){
        ss.removeStudent(uid, cid);
        return "redirect:/main";
    }

    @RequestMapping("/modify/{uid}")
    public String modify(@PathVariable Integer uid, Model model){
        Student stu = ss.queryStudentById(uid);
        model.addAttribute("stu", stu);

        List<Clazz> clazzList = cs.getAllClazz();
        model.addAttribute("clazzList", clazzList);

        return "modify";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(Student stu){ //
        ss.saveStudent(stu);
        return "redirect:/main";
    }

    @RequestMapping("/add")
    public String add(Model model){
        List<Clazz> clazzList = cs.getAllClazz();
        model.addAttribute("stu", new Student());
        model.addAttribute("clazzList", clazzList);
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Student student){  // clazz.id
        // 增加学生
        ss.addStudent(student);
        return "redirect:/main";
    }

    /**
     * 处理用户上传头像的请求
     * @param uid
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload")
    public String upload(Integer uid,
                         MultipartFile file,
                         HttpServletRequest request){
        // 把文件放在assets的img里面
        Student stu = ss.queryStudentById(uid);

        // 这里存储文件，必须用绝对路径
        String path = request.getServletContext().getRealPath("/assets/img");

        try {
            // 把文件写入相应的磁盘目录里面
            // MultipartFile如何把文件写入目录中
            File imageFile = new File(path, file.getOriginalFilename());
            file.transferTo(imageFile);

            // 把文件名字写入数据库
            stu.setImg(file.getOriginalFilename());
            ss.saveStudent(stu);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/main";
    }


    @RequestMapping("/uploadfile")
    public String uploadFile(MultipartFile[] files, HttpServletRequest request){
        // 这里存储文件，在assets/file路径下面
        String path = request.getServletContext().getRealPath("/assets/file");

        try {

            for (MultipartFile file : files) {
                if(!file.getOriginalFilename().isEmpty()){
                    file.transferTo(new File(path, file.getOriginalFilename()));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/main";
    }

    /**
     * 文件下载的功能
     * @param fileName
     * @param session
     * @param response
     * @return
     */
    @RequestMapping("/download")
    public String downLoadFile(String fileName, HttpSession session, HttpServletResponse response){
        String dataDirectory = session.getServletContext().getRealPath("/assets/file/");
        File file = new File(dataDirectory + fileName);
        System.out.println("filepath:" + fileName);
        System.out.println("realpath:" + file.getAbsolutePath());

        if(file.exists()){
            // application/text/html text/json
            response.setContentType("application/octet-stream");
            try {
                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setHeader("Content-Length", String.valueOf(file.length()));

            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try{
                bis = new BufferedInputStream(new FileInputStream(file));
                byte[] bytes = new byte[bis.available()];
                bis.read(bytes);
                response.getOutputStream().write(bytes);
            }catch(IOException ignored){

            }finally {
                try{
                    if(bis != null){
                        bis.close();
                    }
                }catch(IOException ignored){

                }
            }
        }

        return "redirect:/main";
    }

}
