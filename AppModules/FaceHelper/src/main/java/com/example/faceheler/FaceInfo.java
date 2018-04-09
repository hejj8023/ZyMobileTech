package com.example.faceheler;

/**
 * Created by example 2018/4/9.
 */

public class FaceInfo {
    public String faceName;

    public String faceResName;

    public FaceInfo() {
    }

    public FaceInfo(String faceName, String faceResName) {
        this.faceName = faceName;
        this.faceResName = faceResName;
    }

    public String getFaceName() {
        return faceName;
    }

    public void setFaceName(String faceName) {
        this.faceName = faceName;
    }

    public String getFaceResName() {
        return faceResName;
    }

    public void setFaceResName(String faceResName) {
        this.faceResName = faceResName;
    }

    @Override
    public String toString() {
        return "FaceInfo{" +
                "faceName='" + faceName + '\'' +
                ", faceResName='" + faceResName + '\'' +
                '}';
    }
}
