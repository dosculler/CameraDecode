/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sourceforge.zbar.android;

/**
 * The main settings activity.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 * @author Sean Owen
 */
public final class PreferencesActivity {
/**********whole newtologic decode and mask frequently used decode like QR**********/
  public static final String KEY_DECODE_AZTEC	 	= "preferences_decode_AZTEC";
  //public static final String KEY_DECODE_CODABAR	 	= "preferences_decode_CODABAR";
  public static final String KEY_DECODE_CODE11	 	= "preferences_decode_CODE11";
  //public static final String KEY_DECODE_CODE128		= "preferences_decode_CODE128";
  //public static final String KEY_DECODE_CODE39		= "preferences_decode_CODE39";
  public static final String KEY_DECODE_CODE49		= "preferences_decode_CODE49";
  //public static final String KEY_DECODE_CODE93		= "preferences_decode_CODE93";
  //public static final String KEY_DECODE_COMPOSITE	= "preferences_decode_COMPOSITE";
  public static final String KEY_DECODE_DATAMATRIX	= "preferences_decode_DATAMATRIX";
  //public static final String KEY_DECODE_EAN8		= "preferences_decode_EAN8";
  //public static final String KEY_DECODE_EAN13		= "preferences_decode_EAN13";
  public static final String KEY_DECODE_INT25		= "preferences_decode_INT25";
  public static final String KEY_DECODE_MAXICODE	= "preferences_decode_MAXICODE";
  public static final String KEY_DECODE_MICROPDF	= "preferences_decode_MICROPDF";
  public static final String KEY_DECODE_OCR			= "preferences_decode_OCR";
  //public static final String KEY_DECODE_PDF417		= "preferences_decode_PDF417";
  public static final String KEY_DECODE_POSTNET		= "preferences_decode_POSTNET";
  //public static final String KEY_DECODE_QR			= "preferences_decode_QR";
  //public static final String KEY_DECODE_RSS			= "preferences_decode_RSS";
  //public static final String KEY_DECODE_UPCA		= "preferences_decode_UPCA";
  //public static final String KEY_DECODE_UPCE0		= "preferences_decode_UPCE0";
  //public static final String KEY_DECODE_UPCE1		= "preferences_decode_UPCE1";
  public static final String KEY_DECODE_ISBT		= "preferences_decode_ISBT";
  public static final String KEY_DECODE_BPO			= "preferences_decode_BPO";
  public static final String KEY_DECODE_CANPOST		= "preferences_decode_CANPOST";
  public static final String KEY_DECODE_AUSPOST		= "preferences_decode_AUSPOST";
  public static final String KEY_DECODE_IATA25		= "preferences_decode_IATA25";
  public static final String KEY_DECODE_CODABLOCK	= "preferences_decode_CODABLOCK";
  public static final String KEY_DECODE_JAPOST		= "preferences_decode_JAPOST";
  public static final String KEY_DECODE_PLANET		= "preferences_decode_PLANET";
  public static final String KEY_DECODE_DUTCHPOST	= "preferences_decode_DUTCHPOST";
  public static final String KEY_DECODE_MSI			= "preferences_decode_MSI";
  public static final String KEY_DECODE_TLCODE39	= "preferences_decode_TLCODE39";
  public static final String KEY_DECODE_TRIOPTIC	= "preferences_decode_TRIOPTIC";
  public static final String KEY_DECODE_CODE32		= "preferences_decode_CODE32";
  public static final String KEY_DECODE_STRT25		= "preferences_decode_STRT25";
  public static final String KEY_DECODE_MATRIX25	= "preferences_decode_MATRIX25";
  public static final String KEY_DECODE_PLESSEY		= "preferences_decode_PLESSEY";
  public static final String KEY_DECODE_CHINAPOST	= "preferences_decode_CHINAPOST";
  public static final String KEY_DECODE_KOREAPOST	= "preferences_decode_KOREAPOST";
  public static final String KEY_DECODE_TELEPEN		= "preferences_decode_TELEPEN";
  public static final String KEY_DECODE_CODE16K		= "preferences_decode_CODE16K";
  public static final String KEY_DECODE_POSICODE	= "preferences_decode_POSICODE";
  public static final String KEY_DECODE_COUPONCODE	= "preferences_decode_COUPONCODE";
  public static final String KEY_DECODE_USPS4CB		= "preferences_decode_USPS4CB";
  public static final String KEY_DECODE_IDTAG		= "preferences_decode_IDTAG";
  public static final String KEY_DECODE_LABEL		= "preferences_decode_LABEL";
  public static final String KEY_DECODE_GS1_128		= "preferences_decode_GS1_128";
  public static final String KEY_DECODE_HANXIN		= "preferences_decode_HANXIN";
  public static final String KEY_DECODE_GRIDMATRIX	= "preferences_decode_GRIDMATRIX";
  public static final String KEY_DECODE_POSTALS		= "preferences_decode_POSTALS";
  public static final String KEY_DECODE_POSTALS1	= "preferences_decode_POSTALS1";
  public static final String KEY_DECODE_BOLOGIES	= "preferences_decode_BOLOGIES";
  /**********end: whole newtologic decode and mask frequently used decode like QR**********/

  public static final String KEY_CUSTOM_PRODUCT_SEARCH = "preferences_custom_product_search";

  public static final String KEY_PLAY_BEEP = "preferences_play_beep";
  public static final String KEY_VIBRATE = "preferences_vibrate";
  public static final String KEY_COPY_TO_CLIPBOARD = "preferences_copy_to_clipboard";
  public static final String KEY_FRONT_LIGHT_MODE = "preferences_front_light_mode";
  public static final String KEY_BULK_MODE = "preferences_bulk_mode";
  public static final String KEY_BULK_MODE_PERIOD = "preferences_bulk_mode_period";
  public static final String KEY_REMEMBER_DUPLICATES = "preferences_remember_duplicates";
  public static final String KEY_ENABLE_HISTORY = "preferences_history";
  public static final String KEY_SUPPLEMENTAL = "preferences_supplemental";
  public static final String KEY_AUTO_FOCUS = "preferences_auto_focus";
  public static final String KEY_BEEP = "preferences_beep";
  public static final String KEY_FLASH_MODE = "preferences_flash_mode";
  //public static final String KEY_USE_RARE_DECODE = "preferences_use_rare_decode";
  public static final String KEY_USED_FRONT_CCD = "preferences_use_front_ccd";
  public static final String KEY_INVERT_SCAN = "preferences_invert_scan";  
  public static final String KEY_SEARCH_COUNTRY = "preferences_search_country";
  public static final String KEY_DISABLE_AUTO_ORIENTATION = "preferences_orientation";

  public static final String KEY_DISABLE_CONTINUOUS_FOCUS = "preferences_disable_continuous_focus";
  public static final String KEY_DISABLE_EXPOSURE = "preferences_disable_exposure";
  public static final String KEY_DISABLE_METERING = "preferences_disable_metering";
  public static final String KEY_DISABLE_BARCODE_SCENE_MODE = "preferences_disable_barcode_scene_mode";
  public static final String KEY_AUTO_OPEN_WEB = "preferences_auto_open_web";

}
