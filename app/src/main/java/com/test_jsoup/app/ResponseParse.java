package com.test_jsoup.app;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class ResponseParse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("player")
    @Expose
    private Player player;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("captions")
    @Expose
    private List<Object> captions = null;
    @SerializedName("is_vr")
    @Expose
    private Boolean isVr;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public List<Object> getCaptions() {
        return captions;
    }

    public void setCaptions(List<Object> captions) {
        this.captions = captions;
    }

    public Boolean getIsVr() {
        return isVr;
    }

    public void setIsVr(Boolean isVr) {
        this.isVr = isVr;
    }


    public class Player {

        @SerializedName("poster_file")
        @Expose
        private String posterFile;
        @SerializedName("logo_file")
        @Expose
        private String logoFile;
        @SerializedName("logo_position")
        @Expose
        private String logoPosition;
        @SerializedName("logo_link")
        @Expose
        private String logoLink;
        @SerializedName("logo_margin")
        @Expose
        private Integer logoMargin;
        @SerializedName("aspectratio")
        @Expose
        private String aspectratio;
        @SerializedName("powered_text")
        @Expose
        private String poweredText;
        @SerializedName("powered_url")
        @Expose
        private String poweredUrl;
        @SerializedName("css_background")
        @Expose
        private String cssBackground;
        @SerializedName("css_text")
        @Expose
        private String cssText;
        @SerializedName("css_menu")
        @Expose
        private String cssMenu;
        @SerializedName("css_mntext")
        @Expose
        private String cssMntext;
        @SerializedName("css_caption")
        @Expose
        private String cssCaption;
        @SerializedName("css_cttext")
        @Expose
        private String cssCttext;
        @SerializedName("css_ctsize")
        @Expose
        private String cssCtsize;
        @SerializedName("css_ctopacity")
        @Expose
        private String cssCtopacity;
        @SerializedName("css_icon")
        @Expose
        private String cssIcon;
        @SerializedName("css_ichover")
        @Expose
        private String cssIchover;
        @SerializedName("css_tsprogress")
        @Expose
        private String cssTsprogress;
        @SerializedName("css_tsrail")
        @Expose
        private String cssTsrail;
        @SerializedName("css_button")
        @Expose
        private String cssButton;
        @SerializedName("css_bttext")
        @Expose
        private String cssBttext;
        @SerializedName("opt_autostart")
        @Expose
        private Boolean optAutostart;
        @SerializedName("opt_title")
        @Expose
        private Boolean optTitle;
        @SerializedName("opt_quality")
        @Expose
        private Boolean optQuality;
        @SerializedName("opt_caption")
        @Expose
        private Boolean optCaption;
        @SerializedName("opt_download")
        @Expose
        private Boolean optDownload;
        @SerializedName("opt_sharing")
        @Expose
        private Boolean optSharing;
        @SerializedName("opt_playrate")
        @Expose
        private Boolean optPlayrate;
        @SerializedName("opt_mute")
        @Expose
        private Boolean optMute;
        @SerializedName("opt_loop")
        @Expose
        private Boolean optLoop;
        @SerializedName("opt_vr")
        @Expose
        private Boolean optVr;
        @SerializedName("opt_cast")
        @Expose
        private OptCast optCast;
        @SerializedName("opt_nodefault")
        @Expose
        private Boolean optNodefault;
        @SerializedName("opt_forceposter")
        @Expose
        private Boolean optForceposter;
        @SerializedName("opt_parameter")
        @Expose
        private Boolean optParameter;
        @SerializedName("restrict_domain")
        @Expose
        private String restrictDomain;
        @SerializedName("restrict_action")
        @Expose
        private String restrictAction;
        @SerializedName("restrict_target")
        @Expose
        private String restrictTarget;
        @SerializedName("adb_enable")
        @Expose
        private Boolean adbEnable;
        @SerializedName("adb_offset")
        @Expose
        private String adbOffset;
        @SerializedName("adb_text")
        @Expose
        private String adbText;
        @SerializedName("ads_adult")
        @Expose
        private Boolean adsAdult;
        @SerializedName("ads_pop")
        @Expose
        private Boolean adsPop;
        @SerializedName("ads_vast")
        @Expose
        private Boolean adsVast;
        @SerializedName("ads_free")
        @Expose
        private Integer adsFree;
        @SerializedName("trackingId")
        @Expose
        private String trackingId;
        @SerializedName("viewId")
        @Expose
        private String viewId;
        @SerializedName("income")
        @Expose
        private Boolean income;
        @SerializedName("incomePop")
        @Expose
        private Boolean incomePop;
        @SerializedName("resume_text")
        @Expose
        private String resumeText;
        @SerializedName("resume_yes")
        @Expose
        private String resumeYes;
        @SerializedName("resume_no")
        @Expose
        private String resumeNo;
        @SerializedName("resume_enable")
        @Expose
        private Boolean resumeEnable;
        @SerializedName("css_ctedge")
        @Expose
        private String cssCtedge;
        @SerializedName("logger")
        @Expose
        private String logger;
        @SerializedName("revenue")
        @Expose
        private String revenue;
        @SerializedName("revenue_fallback")
        @Expose
        private String revenueFallback;
        @SerializedName("revenue_track")
        @Expose
        private String revenueTrack;

        public String getPosterFile() {
            return posterFile;
        }

        public void setPosterFile(String posterFile) {
            this.posterFile = posterFile;
        }

        public String getLogoFile() {
            return logoFile;
        }

        public void setLogoFile(String logoFile) {
            this.logoFile = logoFile;
        }

        public String getLogoPosition() {
            return logoPosition;
        }

        public void setLogoPosition(String logoPosition) {
            this.logoPosition = logoPosition;
        }

        public String getLogoLink() {
            return logoLink;
        }

        public void setLogoLink(String logoLink) {
            this.logoLink = logoLink;
        }

        public Integer getLogoMargin() {
            return logoMargin;
        }

        public void setLogoMargin(Integer logoMargin) {
            this.logoMargin = logoMargin;
        }

        public String getAspectratio() {
            return aspectratio;
        }

        public void setAspectratio(String aspectratio) {
            this.aspectratio = aspectratio;
        }

        public String getPoweredText() {
            return poweredText;
        }

        public void setPoweredText(String poweredText) {
            this.poweredText = poweredText;
        }

        public String getPoweredUrl() {
            return poweredUrl;
        }

        public void setPoweredUrl(String poweredUrl) {
            this.poweredUrl = poweredUrl;
        }

        public String getCssBackground() {
            return cssBackground;
        }

        public void setCssBackground(String cssBackground) {
            this.cssBackground = cssBackground;
        }

        public String getCssText() {
            return cssText;
        }

        public void setCssText(String cssText) {
            this.cssText = cssText;
        }

        public String getCssMenu() {
            return cssMenu;
        }

        public void setCssMenu(String cssMenu) {
            this.cssMenu = cssMenu;
        }

        public String getCssMntext() {
            return cssMntext;
        }

        public void setCssMntext(String cssMntext) {
            this.cssMntext = cssMntext;
        }

        public String getCssCaption() {
            return cssCaption;
        }

        public void setCssCaption(String cssCaption) {
            this.cssCaption = cssCaption;
        }

        public String getCssCttext() {
            return cssCttext;
        }

        public void setCssCttext(String cssCttext) {
            this.cssCttext = cssCttext;
        }

        public String getCssCtsize() {
            return cssCtsize;
        }

        public void setCssCtsize(String cssCtsize) {
            this.cssCtsize = cssCtsize;
        }

        public String getCssCtopacity() {
            return cssCtopacity;
        }

        public void setCssCtopacity(String cssCtopacity) {
            this.cssCtopacity = cssCtopacity;
        }

        public String getCssIcon() {
            return cssIcon;
        }

        public void setCssIcon(String cssIcon) {
            this.cssIcon = cssIcon;
        }

        public String getCssIchover() {
            return cssIchover;
        }

        public void setCssIchover(String cssIchover) {
            this.cssIchover = cssIchover;
        }

        public String getCssTsprogress() {
            return cssTsprogress;
        }

        public void setCssTsprogress(String cssTsprogress) {
            this.cssTsprogress = cssTsprogress;
        }

        public String getCssTsrail() {
            return cssTsrail;
        }

        public void setCssTsrail(String cssTsrail) {
            this.cssTsrail = cssTsrail;
        }

        public String getCssButton() {
            return cssButton;
        }

        public void setCssButton(String cssButton) {
            this.cssButton = cssButton;
        }

        public String getCssBttext() {
            return cssBttext;
        }

        public void setCssBttext(String cssBttext) {
            this.cssBttext = cssBttext;
        }

        public Boolean getOptAutostart() {
            return optAutostart;
        }

        public void setOptAutostart(Boolean optAutostart) {
            this.optAutostart = optAutostart;
        }

        public Boolean getOptTitle() {
            return optTitle;
        }

        public void setOptTitle(Boolean optTitle) {
            this.optTitle = optTitle;
        }

        public Boolean getOptQuality() {
            return optQuality;
        }

        public void setOptQuality(Boolean optQuality) {
            this.optQuality = optQuality;
        }

        public Boolean getOptCaption() {
            return optCaption;
        }

        public void setOptCaption(Boolean optCaption) {
            this.optCaption = optCaption;
        }

        public Boolean getOptDownload() {
            return optDownload;
        }

        public void setOptDownload(Boolean optDownload) {
            this.optDownload = optDownload;
        }

        public Boolean getOptSharing() {
            return optSharing;
        }

        public void setOptSharing(Boolean optSharing) {
            this.optSharing = optSharing;
        }

        public Boolean getOptPlayrate() {
            return optPlayrate;
        }

        public void setOptPlayrate(Boolean optPlayrate) {
            this.optPlayrate = optPlayrate;
        }

        public Boolean getOptMute() {
            return optMute;
        }

        public void setOptMute(Boolean optMute) {
            this.optMute = optMute;
        }

        public Boolean getOptLoop() {
            return optLoop;
        }

        public void setOptLoop(Boolean optLoop) {
            this.optLoop = optLoop;
        }

        public Boolean getOptVr() {
            return optVr;
        }

        public void setOptVr(Boolean optVr) {
            this.optVr = optVr;
        }

        public OptCast getOptCast() {
            return optCast;
        }

        public void setOptCast(OptCast optCast) {
            this.optCast = optCast;
        }

        public Boolean getOptNodefault() {
            return optNodefault;
        }

        public void setOptNodefault(Boolean optNodefault) {
            this.optNodefault = optNodefault;
        }

        public Boolean getOptForceposter() {
            return optForceposter;
        }

        public void setOptForceposter(Boolean optForceposter) {
            this.optForceposter = optForceposter;
        }

        public Boolean getOptParameter() {
            return optParameter;
        }

        public void setOptParameter(Boolean optParameter) {
            this.optParameter = optParameter;
        }

        public String getRestrictDomain() {
            return restrictDomain;
        }

        public void setRestrictDomain(String restrictDomain) {
            this.restrictDomain = restrictDomain;
        }

        public String getRestrictAction() {
            return restrictAction;
        }

        public void setRestrictAction(String restrictAction) {
            this.restrictAction = restrictAction;
        }

        public String getRestrictTarget() {
            return restrictTarget;
        }

        public void setRestrictTarget(String restrictTarget) {
            this.restrictTarget = restrictTarget;
        }

        public Boolean getAdbEnable() {
            return adbEnable;
        }

        public void setAdbEnable(Boolean adbEnable) {
            this.adbEnable = adbEnable;
        }

        public String getAdbOffset() {
            return adbOffset;
        }

        public void setAdbOffset(String adbOffset) {
            this.adbOffset = adbOffset;
        }

        public String getAdbText() {
            return adbText;
        }

        public void setAdbText(String adbText) {
            this.adbText = adbText;
        }

        public Boolean getAdsAdult() {
            return adsAdult;
        }

        public void setAdsAdult(Boolean adsAdult) {
            this.adsAdult = adsAdult;
        }

        public Boolean getAdsPop() {
            return adsPop;
        }

        public void setAdsPop(Boolean adsPop) {
            this.adsPop = adsPop;
        }

        public Boolean getAdsVast() {
            return adsVast;
        }

        public void setAdsVast(Boolean adsVast) {
            this.adsVast = adsVast;
        }

        public Integer getAdsFree() {
            return adsFree;
        }

        public void setAdsFree(Integer adsFree) {
            this.adsFree = adsFree;
        }

        public String getTrackingId() {
            return trackingId;
        }

        public void setTrackingId(String trackingId) {
            this.trackingId = trackingId;
        }

        public String getViewId() {
            return viewId;
        }

        public void setViewId(String viewId) {
            this.viewId = viewId;
        }

        public Boolean getIncome() {
            return income;
        }

        public void setIncome(Boolean income) {
            this.income = income;
        }

        public Boolean getIncomePop() {
            return incomePop;
        }

        public void setIncomePop(Boolean incomePop) {
            this.incomePop = incomePop;
        }

        public String getResumeText() {
            return resumeText;
        }

        public void setResumeText(String resumeText) {
            this.resumeText = resumeText;
        }

        public String getResumeYes() {
            return resumeYes;
        }

        public void setResumeYes(String resumeYes) {
            this.resumeYes = resumeYes;
        }

        public String getResumeNo() {
            return resumeNo;
        }

        public void setResumeNo(String resumeNo) {
            this.resumeNo = resumeNo;
        }

        public Boolean getResumeEnable() {
            return resumeEnable;
        }

        public void setResumeEnable(Boolean resumeEnable) {
            this.resumeEnable = resumeEnable;
        }

        public String getCssCtedge() {
            return cssCtedge;
        }

        public void setCssCtedge(String cssCtedge) {
            this.cssCtedge = cssCtedge;
        }

        public String getLogger() {
            return logger;
        }

        public void setLogger(String logger) {
            this.logger = logger;
        }

        public String getRevenue() {
            return revenue;
        }

        public void setRevenue(String revenue) {
            this.revenue = revenue;
        }

        public String getRevenueFallback() {
            return revenueFallback;
        }

        public void setRevenueFallback(String revenueFallback) {
            this.revenueFallback = revenueFallback;
        }

        public String getRevenueTrack() {
            return revenueTrack;
        }

        public void setRevenueTrack(String revenueTrack) {
            this.revenueTrack = revenueTrack;
        }

    }


    public class Datum {

        @SerializedName("file")
        @Expose
        private String file;
        @SerializedName("label")
        @Expose
        private String label;
        @SerializedName("type")
        @Expose
        private String type;

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }

    @Generated("jsonschema2pojo")
    public class OptCast {

        @SerializedName("appid")
        @Expose
        private String appid;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

    }
}
