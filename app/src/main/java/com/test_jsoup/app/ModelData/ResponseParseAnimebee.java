package com.test_jsoup.app.ModelData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseParseAnimebee {

    @SerializedName("@context")
    @Expose
    private String context;
    @SerializedName("@graph")
    @Expose
    private List<Graph> graph = null;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<Graph> getGraph() {
        return graph;
    }

    public void setGraph(List<Graph> graph) {
        this.graph = graph;
    }

    public class Graph {

        @SerializedName("@context")
        @Expose
        private String context;
        @SerializedName("@type")
        @Expose
        private String type;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("episodeNumber")
        @Expose
        private String episodeNumber;
        @SerializedName("position")
        @Expose
        private String position;
        @SerializedName("dateModified")
        @Expose
        private String dateModified;
        @SerializedName("thumbnailUrl")
        @Expose
        private String thumbnailUrl;
        @SerializedName("director")
        @Expose
        private String director;
        @SerializedName("datePublished")
        @Expose
        private String datePublished;
        @SerializedName("potentialAction")
        @Expose
        private PotentialAction potentialAction;
        @SerializedName("video")
        @Expose
        private Video video;
        @SerializedName("inLanguage")
        @Expose
        private String inLanguage;
        @SerializedName("subtitleLanguage")
        @Expose
        private String subtitleLanguage;

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEpisodeNumber() {
            return episodeNumber;
        }

        public void setEpisodeNumber(String episodeNumber) {
            this.episodeNumber = episodeNumber;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getDateModified() {
            return dateModified;
        }

        public void setDateModified(String dateModified) {
            this.dateModified = dateModified;
        }

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getDatePublished() {
            return datePublished;
        }

        public void setDatePublished(String datePublished) {
            this.datePublished = datePublished;
        }

        public PotentialAction getPotentialAction() {
            return potentialAction;
        }

        public void setPotentialAction(PotentialAction potentialAction) {
            this.potentialAction = potentialAction;
        }

        public Video getVideo() {
            return video;
        }

        public void setVideo(Video video) {
            this.video = video;
        }

        public String getInLanguage() {
            return inLanguage;
        }

        public void setInLanguage(String inLanguage) {
            this.inLanguage = inLanguage;
        }

        public String getSubtitleLanguage() {
            return subtitleLanguage;
        }

        public void setSubtitleLanguage(String subtitleLanguage) {
            this.subtitleLanguage = subtitleLanguage;
        }

    }


    public class PotentialAction {

        @SerializedName("@type")
        @Expose
        private String type;
        @SerializedName("target")
        @Expose
        private String target;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

    }

    public class Video {

        @SerializedName("@type")
        @Expose
        private String type;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("uploadDate")
        @Expose
        private String uploadDate;
        @SerializedName("thumbnailUrl")
        @Expose
        private String thumbnailUrl;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("videoQuality")
        @Expose
        private String videoQuality;
        @SerializedName("embedUrl")
        @Expose
        private String embedUrl;
        @SerializedName("potentialAction")
        @Expose
        private PotentialAction__1 potentialAction;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUploadDate() {
            return uploadDate;
        }

        public void setUploadDate(String uploadDate) {
            this.uploadDate = uploadDate;
        }

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVideoQuality() {
            return videoQuality;
        }

        public void setVideoQuality(String videoQuality) {
            this.videoQuality = videoQuality;
        }

        public String getEmbedUrl() {
            return embedUrl;
        }

        public void setEmbedUrl(String embedUrl) {
            this.embedUrl = embedUrl;
        }

        public PotentialAction__1 getPotentialAction() {
            return potentialAction;
        }

        public void setPotentialAction(PotentialAction__1 potentialAction) {
            this.potentialAction = potentialAction;
        }
    }

    public class PotentialAction__1 {

        @SerializedName("@type")
        @Expose
        private String type;
        @SerializedName("target")
        @Expose
        private String target;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

    }
}
