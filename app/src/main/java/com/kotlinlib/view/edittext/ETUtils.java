package com.kotlinlib.view.edittext;

import android.content.Context;
import android.content.IntentFilter;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by Administrator on 2018/3/20 0020.
 */

public class ETUtils {

    private static CharSequence wordNum;
    private static int words;
    private static int selectionStart;
    private static int selectionEnd;

    /**
     * 监听打字数量
     * @param et
     * @param tvWords
     * @param maxWords
     */
    public static void listenerWordsInput(final EditText et, final TextView tvWords, final int maxWords){
        et.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                wordNum = s;//实时记录输入的字数
            }

            @Override
            public void afterTextChanged(Editable s) {
                String editable = et.getText().toString();
                //String str = editable;
                String str = stringFilter(editable);
                if (!editable.equals(str)) {
                    et.setText(str);
                    //设置新的光标所在位置
                    et.setSelection(str.length());
                } else {
                    words = s.length();
                    //TextView显示剩余字数
                    tvWords.setText(s.length() + "/"+maxWords);
                    selectionStart = et.getSelectionStart();
                    selectionEnd = et.getSelectionEnd();
                    if (wordNum.length() > maxWords) {
                        //删除多余输入的字（不会显示出来）
                        s.delete(selectionStart - 1, selectionEnd);
                        int tempSelection = selectionEnd;
                        et.setText(s);
                        et.setSelection(tempSelection);//设置光标在最后
                    }
                }
            }
        });
    }

    /**
     * 设置正则表达式来过滤输入字符
     * @param et
     * @param regex
     */
    public static void setRegex(EditText et, String regex, int maxLength){
        InputFilter inputFilter = (source, start, end, dest, dstart, dend) -> {
            Log.d("REGEX", source.toString()+" "+start+" "+end+" "+dstart+" "+dend);
            if(ValidateUtils.isMatches(source.toString(), regex)){
                return null;//如果符合要求则不过滤
            }else {
                return "";//如果不符合要求则进行过滤
            }
        };
        InputFilter.LengthFilter filter2 = new InputFilter.LengthFilter(maxLength);
        InputFilter[] filters = {inputFilter,filter2 };
        et.setFilters(filters);
    }

    /**
     * 匹配正则表达式
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static String stringFilter(String str) throws PatternSyntaxException {
        // 只允许字母、数字和汉字
        String   regEx  =  "[^a-zA-Z0-9\u4E00-\u9FA5.,。，]";
        //String regEx = "[^a-zA-Z0-9\u4E00-\u9FA5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 给金额添加逗号断点
     * @param value
     * @return
     */
    public static String addBreaksForMoney(String value){
        if(value.contains(".")){
            return valueFormatWithTwo(value);
        }else {
            return valueFormat(value);
        }
    }

    public static String valueFormatWithTwo(String value) {
        if (TextUtils.isEmpty(value)) {
            return "0.00";
        }
        BigDecimal bd = new BigDecimal(value);
        DecimalFormat df = new DecimalFormat("##,###,##0.00");//小数点点不够两位补0，例如："0" --> 0.00（个位数补成0因为传入的是0则会显示成：.00，所以各位也补0；）
        String xs = df.format(bd.setScale(2, BigDecimal.ROUND_DOWN));//直接截取小数点后两位（不四舍五入）
        return xs;
    }

    public static String valueFormat(String value) {
        if (TextUtils.isEmpty(value)) {
            return "0.00";
        }
        BigDecimal bd = new BigDecimal(value);
        DecimalFormat df = new DecimalFormat("##,###,##0");//小数点点不够两位补0，例如："0" --> 0.00（个位数补成0因为传入的是0则会显示成：.00，所以各位也补0；）
        String xs = df.format(bd.setScale(2, BigDecimal.ROUND_DOWN));//直接截取小数点后两位（不四舍五入）
        return xs;
    }

    /**
     * 限制输入的数字大小
     * @param edit
     * @param MIN_MARK
     * @param MAX_MARK
     */
    public static void setRegion(final EditText edit, final double MIN_MARK, final double MAX_MARK) {
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 1) {
                    if (MIN_MARK != -1 && MAX_MARK != -1) {
                        double num = Double.parseDouble(s.toString());
                        if (num > MAX_MARK) {
                            s = String.valueOf(MAX_MARK);
                            edit.setText(s);
                        } else if (num < MIN_MARK) {
                            s = String.valueOf(MIN_MARK);
                            edit.setText(s);
                        }
                        edit.setSelection(s.length());
                    }
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && !s.equals("")) {
                    if (MIN_MARK != -1 && MAX_MARK != -1) {
                        double markVal = 0;
                        try {
                            markVal = Double.parseDouble(s.toString());
                        } catch (NumberFormatException e) {
                            markVal = 0;
                        }
                        if (markVal > MAX_MARK) {
                            edit.setText(String.valueOf(MAX_MARK));
                            edit.setSelection(String.valueOf(MAX_MARK).length());
                        }
                        return;
                    }
                }
            }
        });
    }

}
