package csf.day37.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

@Service
public class S3Service {
    @Autowired
    private AmazonS3 s3Client;

    @Value("${do.storage.bucket}")
    private String bucketName;
    @Value("${do.storage.endpoint}")
    private String endpoint;

    public String upload(MultipartFile file, String comments, String postId) throws IOException {
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("comments", comments);
        userData.put("postId", postId);
        userData.put("fileName", file.getOriginalFilename());
        userData.put("uploadDateTime", LocalDateTime.now().toString());

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        metadata.setUserMetadata(userData);

        StringTokenizer tk = new StringTokenizer(file.getOriginalFilename(), ".");
        
        boolean firstToken = true;
        
        String fileNameExt = "";
        while (tk.hasMoreTokens()) {
            if(firstToken) {
                fileNameExt = tk.nextToken();

                firstToken = false;
            } else
                fileNameExt = tk.nextToken() + "." + fileNameExt;
        }
        System.out.println(fileNameExt);

        if(fileNameExt.equals("blob"))
            fileNameExt = fileNameExt + ".png";

        PutObjectRequest req = new PutObjectRequest(bucketName, 
                                                    "image%s.%s".formatted(postId, fileNameExt), 
                                                    file.getInputStream(), metadata);
        req.withCannedAcl(CannedAccessControlList.PublicRead);
        
        PutObjectResult result = s3Client.putObject(req);
        
        // return URL of obj stored
        return "https://%s.%s/image%s.%s".formatted(bucketName, endpoint, postId, fileNameExt);
    }
}
