package gtsoffenbach.nfc_game_admin_app_prototype;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Kern on 21.07.2014.
 */
public class nfc {

        private static final String[] URI_PREFIXES = new String[]{
                "",
                "http://www.",
                "https://www.",
                "http://",
                "https://",
                "tel:",
                "mailto:",
                "ftp://anonymous:anonymous@",
                "ftp://ftp.",
                "ftps://",
                "sftp://",
                "smb://",
                "nfs://",
                "ftp://",
                "dav://",
                "news:",
                "telnet://",
                "imap:",
                "rtsp://",
                "urn:",
                "pop:",
                "sip:",
                "sips:",
                "tftp:",
                "btspp://",
                "btl2cap://",
                "btgoep://",
                "tcpobex://",
                "irdaobex://",
                "file://",
                "urn:epc:id:",
                "urn:epc:tag:",
                "urn:epc:pat:",
                "urn:epc:raw:",
                "urn:epc:",
                "urn:nfc:",
        };

        public static NdefMessage fromUrl(URL url) {
            return fromUri(url.toString());
        }

        public static NdefMessage fromUri(String uri) {
            try {
                int prefix = 0;
                for (int i = 1; i < URI_PREFIXES.length; i++) {
                    if (uri.startsWith(URI_PREFIXES[i])) {
                        prefix = i;
                        break;
                    }
                }
                if (prefix > 0) uri = uri.substring(URI_PREFIXES[prefix].length());
                int len = uri.length();
                byte[] payload = new byte[len + 1];
                payload[0] = (byte) prefix;
                System.arraycopy(uri.getBytes("UTF-8"), 0, payload, 1, len);
                NdefRecord record = new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_URI,
                        new byte[0], payload);
                NdefRecord[] records = new NdefRecord[]{record};
                return new NdefMessage(records);
            } catch (NoClassDefFoundError e) {
                return null;
            } catch (UnsupportedEncodingException e) {
                System.out.println("UTF-8 Unsopported!!!");
                return null;
            }
        }

        public static NdefMessage fromText(String text, String languageCode) {
            try {
                int languageCodeLength = languageCode.length();
                int textLength = text.length();
                byte[] textPayload = new byte[textLength + 1 + languageCodeLength];
                textPayload[0] = (byte) (0x3F & languageCodeLength); // UTF-8 with the given language code length.
                System.arraycopy(languageCode.getBytes(), 0, textPayload, 1, languageCodeLength);
                System.arraycopy(text.getBytes(), 0, textPayload, 1 + languageCodeLength, textLength);
                NdefRecord record = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
                        NdefRecord.RTD_TEXT, new byte[0], textPayload);
                NdefRecord[] records = new NdefRecord[]{record};
                return new NdefMessage(records);
            } catch (NoClassDefFoundError e) {
                return null;
            }
        }

        public static final NdefMessage getEmptyNdef() {
            byte[] empty = new byte[0];
            NdefRecord[] records = new NdefRecord[1];
            records[0] = new NdefRecord(NdefRecord.TNF_WELL_KNOWN, empty, empty, empty);
            return new NdefMessage(records);
        }










    }


