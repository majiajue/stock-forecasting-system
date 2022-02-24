/*
package com.stockpredict.system.model;

import org.ansj.domain.Term;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.common.io.ClassPathResource;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

*/
/**
 * @author wangzhewen
 * @time 2022/2/20 17:02
 * @description: TODO
 *//*

public class TfModelLoader {
    private final Session session;

    public TfModelLoader(String modelPath, String modelTag) {
        SavedModelBundle modelBundle = SavedModelBundle.load(modelPath, modelTag);
        this.session = modelBundle.session();
    }

    public TfModelLoader(String modelPath) {
        SavedModelBundle modelBundle = SavedModelBundle.load(modelPath, "serve");
        this.session = modelBundle.session();
    }

    public float[] predict(List<Term> terms) {
        int termSize = terms.size();

        byte[][][] wordsBytes = new byte[1][termSize][];
        int[] nwords = new int[]{wordsBytes[0].length};
        for (int i = 0; i < termSize; ++i) {
            byte[] nameBytes = terms.get(i).getName().getBytes(StandardCharsets.UTF_8);
            wordsBytes[0][i] = nameBytes;
        }

        try (Tensor<?> wordsTensor = Tensor.create(wordsBytes);
             Tensor<?> nwordsTensor = Tensor.create(nwords)) {
            Tensor<?> result = session.runner()
                    .feed("words", wordsTensor)
                    .feed("nwords", nwordsTensor)
                    .fetch("predict").run().get(0);
            float[][] preds = new float[1][wordsBytes[0].length];
            result.copyTo(preds);

            // Tensor Close
            result.close();
            return preds[0];
        } catch (Throwable e) {
            // log.error("Model Predict Exception: ", e);
        }
        return null;
    }

    public static void main(String[] args) throws IOException, UnsupportedKerasConfigurationException, InvalidKerasConfigurationException {
        // modelPath是模型在resource下路径，modelTag从模型文件信息中获取
        // SavedModelBundle modelBundle = SavedModelBundle.load("./model/stock_model.pb");
        // TfModelLoader tfModelLoader = new TfModelLoader("/model/stock_model.pb");
        String fullModel = new ClassPathResource("static/stock_model.h5").getFile().getPath();
        MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(fullModel);




    }
}*/
