package com.gtalent.project_sb_aws_s3.file;

public class File {
    private String fileName;
    private byte[] file;

    public File(String fileName, byte[]file){
        this.fileName = fileName;
        this.file = file;
    }

    public String getFileName(){
        return fileName;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file){
        this.file = file;
    }
}

