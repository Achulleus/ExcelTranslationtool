import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClient;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Translate {
    private static final String REGION = "region";
    public Translate(){
    }

    public Map<Integer, List<String>> translate(Map<Integer, List<String>> oldData, String sourceLanguage, String targetLanguage){
        Map<Integer, List<String>> newData = new HashMap<>();

        AWSCredentialsProvider awsCreds = DefaultAWSCredentialsProviderChain.getInstance();

        AmazonTranslate translate = AmazonTranslateClient.builder()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds.getCredentials()))
                .withRegion(REGION)
                .build();

        for(Integer i : oldData.keySet()) {
            newData.put(i, new ArrayList<String>());
            for (String value : oldData.get(i)) {
                TranslateTextRequest request = new TranslateTextRequest()
                        .withText(value)
                        .withSourceLanguageCode(sourceLanguage)
                        .withTargetLanguageCode(targetLanguage);
                TranslateTextResult result = translate.translateText(request);
                newData.get(i).add(result.getTranslatedText());
                //System.out.println(result.getTranslatedText());
            }
        }

        return newData;
    }
}
