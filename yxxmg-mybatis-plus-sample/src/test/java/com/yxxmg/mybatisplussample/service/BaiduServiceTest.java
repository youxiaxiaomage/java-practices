package com.yxxmg.mybatisplussample.service;

import com.alibaba.fastjson.JSONObject;
import com.yxxmg.mybatisplussample.domain.AccessToken;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BaiduServiceTest {
    private static final String RESPONSE =
            "{\"refresh_token\":\"25.e24eb1bf2271a3f17ee62a053bba46d9.315360000.1983181002.282335-28295632\",\"expires_in\":2592000,\"session_key\":\"9mzdWWNgg69frlfKIVNPhHVdyKypHx9ikP\\/TVmF4OYH3bXlKNnnoEZWOnFQL8\\/cMmtrJrCqrYq5yZldjloJ5KkJfqsLGGw==\",\"access_token\":\"24.b9303dff5b5ace1c9115bbbc41c1d1b8.2592000.1670413002.282335-28295632\",\"scope\":\"public vis-ocr_ocr brain_ocr_scope brain_ocr_general brain_ocr_general_basic vis-ocr_business_license brain_ocr_webimage brain_all_scope brain_ocr_idcard brain_ocr_driving_license brain_ocr_vehicle_license vis-ocr_plate_number brain_solution brain_ocr_plate_number brain_ocr_accurate brain_ocr_accurate_basic brain_ocr_receipt brain_ocr_business_license brain_solution_iocr brain_qrcode brain_ocr_handwriting brain_form brain_ocr_passport brain_ocr_vat_invoice brain_numbers brain_ocr_business_card brain_ocr_train_ticket brain_ocr_taxi_receipt vis-ocr_household_register vis-ocr_vis-classify_birth_certificate vis-ocr_\\u53f0\\u6e7e\\u901a\\u884c\\u8bc1 vis-ocr_\\u6e2f\\u6fb3\\u901a\\u884c\\u8bc1 vis-ocr_\\u673a\\u52a8\\u8f66\\u8d2d\\u8f66\\u53d1\\u7968\\u8bc6\\u522b vis-ocr_\\u673a\\u52a8\\u8f66\\u68c0\\u9a8c\\u5408\\u683c\\u8bc1\\u8bc6\\u522b vis-ocr_\\u8f66\\u8f86vin\\u7801\\u8bc6\\u522b vis-ocr_\\u5b9a\\u989d\\u53d1\\u7968\\u8bc6\\u522b vis-ocr_\\u4fdd\\u5355\\u8bc6\\u522b vis-ocr_\\u673a\\u6253\\u53d1\\u7968\\u8bc6\\u522b vis-ocr_\\u884c\\u7a0b\\u5355\\u8bc6\\u522b brain_ocr_vin brain_ocr_quota_invoice brain_ocr_birth_certificate brain_ocr_household_register brain_ocr_HK_Macau_pass brain_ocr_taiwan_pass brain_ocr_vehicle_invoice brain_ocr_vehicle_certificate brain_ocr_air_ticket brain_ocr_invoice brain_ocr_insurance_doc brain_formula brain_seal brain_ocr_facade brain_ocr_meter brain_doc_analysis brain_ocr_webimage_loc brain_bus_ticket brain_toll_invoice brain_ocr_medical_paper brain_ocr_doc_analysis_office brain_ferry_ticket brain_vat_invoice_verification brain_ocr_used_vehicle_invoice brain_ocr_medical_detail brain_vehicle_registration_certificate brain_ocr_online_taxi_itinerary brain_ocr_multi_idcard brain_ocr_mixed_multi_vehicle brain_ocr_weigth_note brain_ocr_ multiple_invoice brain_ocr_social_security_card brain_ocr_medical_report_detection brain_ocr_waybill brain_ocr_medical_summary brain_ocr_brain_shopping_receipt brain_ocr_road_transport_certificate brain_form_table brain_ocr_health_code brain_ocr_covid_test brain_ocr_health_report brain_ocr_three_factors_verification wise_adapt lebo_resource_base lightservice_public hetu_basic lightcms_map_poi kaidian_kaidian ApsMisTest_Test\\u6743\\u9650 vis-classify_flower lpq_\\u5f00\\u653e cop_helloScope ApsMis_fangdi_permission smartapp_snsapi_base smartapp_mapp_dev_manage iop_autocar oauth_tp_app smartapp_smart_game_openapi oauth_sessionkey smartapp_swanid_verify smartapp_opensource_openapi smartapp_opensource_recapi fake_face_detect_\\u5f00\\u653eScope vis-ocr_\\u865a\\u62df\\u4eba\\u7269\\u52a9\\u7406 idl-video_\\u865a\\u62df\\u4eba\\u7269\\u52a9\\u7406 smartapp_component smartapp_search_plugin avatar_video_test b2b_tp_openapi b2b_tp_openapi_online smartapp_gov_aladin_to_xcx\",\"session_secret\":\"49863a6e29e86456d3da3add9a366cff\"}\n";

    @Test
    public void test() {
        AccessToken accessToken = JSONObject.parseObject(RESPONSE, AccessToken.class);
        Assert.assertEquals("24.b9303dff5b5ace1c9115bbbc41c1d1b8.2592000.1670413002.282335-28295632",
                accessToken.getAccessToken());
    }

}