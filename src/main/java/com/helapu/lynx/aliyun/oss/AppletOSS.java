package com.helapu.lynx.aliyun.oss;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.helapu.lynx.config.properties.AliyunProperties;

import java.util.logging.*;

@Service
public class AppletOSS {

//	@Value("${aliyun.oss.endpoint}")
//	private String endpoint;
//	
//	@Value("${aliyun.oss.accessKey-id}")
//	private String accessKeyId;
//	
//	@Value("${aliyun.oss.accessKey-secret}")
//	private String accessKeySecret;
//	
//	@Value("${aliyun.oss.bucket-name}")
//	private String bucketName;
	
	public void justTest(String firstKey) {
		
		
		OSSClient ossClient = new OSSClient(AliyunProperties.getEndpoint(), 
				AliyunProperties.getAccessKeyId(), AliyunProperties.getAccessKeySecret());

        try {
            
        	if (ossClient.doesBucketExist( AliyunProperties.getBucketName() )) {
                System.out.println("您已经创建Bucket: " + AliyunProperties.getBucketName());
            } else {
                System.out.println("您的Bucket不存在，创建Bucket：" + AliyunProperties.getBucketName());
                // 创建Bucket。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
                // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
                ossClient.createBucket(AliyunProperties.getBucketName());
            }

            // 查看Bucket信息。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
            BucketInfo info = ossClient.getBucketInfo(AliyunProperties.getBucketName());
            System.out.println("Bucket " + AliyunProperties.getBucketName() + "的信息如下：");
            System.out.println("\t数据中心：" + info.getBucket().getLocation());
            System.out.println("\t创建时间：" + info.getBucket().getCreationDate());
            System.out.println("\t用户标志：" + info.getBucket().getOwner());

            // 把字符串存入OSS，Object的名称为firstKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
            InputStream is = new ByteArrayInputStream("Hello OSS".getBytes());
            ossClient.putObject(AliyunProperties.getBucketName(), firstKey, is);
            System.out.println("Object：" + firstKey + "存入OSS成功。");

            // 下载文件。详细请参看“SDK手册 > Java-SDK > 下载文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/download_object.html?spm=5176.docoss/sdk/java-sdk/manage_object
            OSSObject ossObject = ossClient.getObject(AliyunProperties.getBucketName(), firstKey);
            InputStream inputStream = ossObject.getObjectContent();
            StringBuilder objectContent = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String line = reader.readLine();
                if (line == null)
                    break;
                objectContent.append(line);
            }
            inputStream.close();
            System.out.println("Object：" + firstKey + "的内容是：" + objectContent);

            // 文件存储入OSS，Object的名称为fileKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
            String fileKey = "README.md";
            ossClient.putObject(AliyunProperties.getBucketName(), fileKey, new File("README.md"));
            System.out.println("Object：" + fileKey + "存入OSS成功。");

            // 查看Bucket中的Object。详细请参看“SDK手册 > Java-SDK > 管理文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_object.html?spm=5176.docoss/sdk/java-sdk/manage_bucket
            ObjectListing objectListing = ossClient.listObjects(AliyunProperties.getBucketName());
            List<OSSObjectSummary> objectSummary = objectListing.getObjectSummaries();
            System.out.println("您有以下Object：");
            for (OSSObjectSummary object : objectSummary) {
                System.out.println("\t" + object.getKey());
            }

            // 删除Object。详细请参看“SDK手册 > Java-SDK > 管理文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_object.html?spm=5176.docoss/sdk/java-sdk/manage_bucket
            ossClient.deleteObject(AliyunProperties.getBucketName(), firstKey);
            System.out.println("删除Object：" + firstKey + "成功。");
            ossClient.deleteObject(AliyunProperties.getBucketName(), fileKey);
            System.out.println("删除Object：" + fileKey + "成功。");

        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
	}
	
	

}

