package com.moerlong.carloan.modular.loan.controller;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.moerlong.carloan.common.controller.ReduceImg;
import com.moerlong.carloan.modular.sport.entity.SQc;
import com.moerlong.carloan.modular.sport.service.SQcService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class PrjFileUploadController {
    private final Logger log = LoggerFactory.getLogger(PrjFileUploadController.class);

    @Value("${file.identity_pic_path}")
    private String idPicPath;
    @Value("${file.liveness_pic_path}")
    private String livenessPicPath;
    @Value("${file.protobuf_path}")
    private String protobufPath;
    @Value("${file.vehicle_pic_path}")
    private String vehiclePicPath;
    @Value("${file.register_pic_path}")
    private String registerPicPath;
    @Value("${file.strong_insure_pic_path}")
    private String strongPicPath;
    @Value("${file.buss_insure_pic_path}")
    private String bussPicPath;


    @Value("${file.identity_pic_url}")
    private String idPicUrl;
    @Value("${file.identity_pic_urlsx}")
    private String idPicUrlsx;
    @Value("${file.liveness_pic_url}")
    private String livenessPicUrl;
    @Value("${file.protobuf_url}")
    private String protobufUrl;
    @Value("${file.vehicle_pic_url}")
    private String vehiclePicUrl;
    @Value("${file.register_pic_url}")
    private String registerPicUrl;
    @Value("${file.strong_insure_pic_url}")
    private String strongPicUrl;
    @Value("${file.buss_insure_pic_url}")
    private String bussPicUrl;

    @Autowired
    SQcService qcService;

    /**
     * 保存文件
     * @param myfile
     * @return
     */
    private Map<String,Object> saveFile(MultipartFile myfile, String fileSubPath, String fileUrl,Integer qcId){
        Map<String,Object> map = new HashMap<>();
        String FilePath ="";
        if (myfile != null && !myfile.isEmpty()) {
            // 获取文件的原始名称
            String oldName = myfile.getOriginalFilename();

            if(oldName.contains("$")||oldName.contains("&")||oldName.contains("#")){
                log.error("===>[saveFile] file error,文件名中包含非法字符");
                map.put("status","1");
                map.put("errMsg","文件名中包含非法字符");
                return map;
            }
            // 获取文件大小
            Long fileSize = myfile.getSize();
            // 获取文件的原始流
            // f.getInputStream()

            // 组装文件存储路径
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = sdf.format(new Date());
            String filePath = fileSubPath + File.separator + dateStr;

            // 创建目录
            File f = new File(filePath);
            if (!f.exists()) {
                f.mkdirs();
            }

            // 生成一个新的不会重复的文件名
            // 1.获取后缀
            String suffix = FilenameUtils.getExtension(myfile.getOriginalFilename());
            // 2.生成新的文件名
            String newFileName = UUID.randomUUID().toString() +"."+suffix;
            // 把上传的文件存储指定位置
            try{
                myfile.transferTo(new File(f, newFileName));
            }catch (Exception e){
                log.error("===>[saveFile] exception e={}", e);
                map.put("status","1");
                map.put("errMsg","上传文件异常！");
            }

           // FilePath=fileUrl+ File.separator + dateStr + File.separator + newFileName;
            String reduceUrl = f+"/"+newFileName;
            FilePath=idPicUrlsx+ "/" + dateStr + "/" + newFileName;
            log.info("上传成功！！文件路径===》{}",FilePath);

            SQc sqc = qcService.selectById(qcId);
            String dljd = sqc.getDljd();
            String dlwd = sqc.getDlwd();
            if(null ==dljd || null ==dlwd || "".equals(dljd)|| "".equals(dlwd)){

            Metadata metadata = null;
            try {
                metadata = JpegMetadataReader.readMetadata(new File(f, newFileName));
                StringBuilder wd = new StringBuilder("");
                StringBuilder jd =  new StringBuilder("");
                for(Directory directory : metadata.getDirectories()){
                    for(Tag tag : directory.getTags()){
                        if("GPS Latitude Ref".equals(tag.getTagName())){//"N" 纬度
                            if("N".equals(tag.getDescription())){
                                wd.append("北纬：");
                            }
                        }

                        if("GPS Latitude".equals(tag.getTagName())){//""纬度
                            wd.append(tag.getDescription());
                        }

                        if("GPS Longitude Ref".equals(tag.getTagName())){//"E" 经度
                            if("E".equals(tag.getDescription())){
                                jd.append("东经：");
                            }
                        }

                        if("GPS Longitude".equals(tag.getTagName())){//""
                            jd.append(tag.getDescription());
                        }
                    }
                }
                map.put("jd",jd.toString());
                map.put("wd",wd.toString());
                sqc.setDljd(jd.toString());
                sqc.setDlwd(wd.toString());
                qcService.updateWithOutNull(sqc);

                //上传成功之后进行图片压缩
//            log.info("压缩之前图片大小==="+new File(reduceUrl).length());
                ReduceImg.reduceImg(reduceUrl,reduceUrl,400,300,null);
//            log.info("压缩之后图片大小==="+new File(reduceUrl).length());
            } catch (JpegProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
            map.put("status","0");
            map.put("filePath",FilePath);

            return map;

        } else {
            map.put("status","1");
            map.put("errMsg","上传文件失败！");
            return map;
        }

    }

    /**
     * 上传身份证信息
     * @param myfile
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value="/prjfile/upload/pic")
    @ResponseBody
    private Object uploadIdCard(@RequestParam(name = "file", required = false) MultipartFile myfile,@RequestParam(name = "qcId",required = false) Integer qcId)  {
        return saveFile(myfile, idPicPath, idPicUrl,qcId);
    }

}
