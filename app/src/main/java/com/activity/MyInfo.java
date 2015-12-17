package com.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.base.BaseActivity;
import com.common.Constant;
import com.common.HavaSdCard;
import com.custom.MaterialDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import huisou.model.R;

/**
 * Created by huisou on 2015/12/16.
 */
public class MyInfo extends BaseActivity implements View.OnClickListener {
    private TextView tv_name,tv_nc,tv_email,tv_sex;
    private ImageView img;
    private Dialog dialog;
    private String logoBase,imgs;
    private SimpleAdapter dialogAda;
    private List<Map<String, String>> gender_list = new ArrayList<>();
    private  String pagename=MyInfo.this.getPackageName();
    private  final String IMGURL = Environment
            .getExternalStorageDirectory() + "/Android/data/"+pagename+"/";
    private static final String IMAGE_FILE_NAME_TEMP = "hh_image.jpg";
    private ListView listView;
    private MaterialDialog materialDialog;
    private String sexid,sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_myinfo, true);
        initView();
    }

    private void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("需要获取");
        toolbar.setTitleTextColor(getResources().getColor(R.color.app_light));
        toolbar.setBackgroundColor(Color.parseColor(Constant.COLOR_BAR));
        toolbar.setNavigationIcon(R.mipmap.right_too);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        tv_name=(TextView)findViewById(R.id.tv_n);
        tv_nc=(TextView)findViewById(R.id.tv_tne);
        tv_email=(TextView)findViewById(R.id.tv_email);
        tv_sex=(TextView)findViewById(R.id.tv_sex);
        img=(ImageView)findViewById(R.id.setting_img);

        View view = getLayoutInflater().inflate(R.layout.pic_show, null);
        dialog = new Dialog(this,
                R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        initDialog(view);
        tv_name.setOnClickListener(this);
        tv_email.setOnClickListener(this);
        tv_nc.setOnClickListener(this);
        tv_email.setOnClickListener(this);
        tv_sex.setOnClickListener(this);
        img.setOnClickListener(this);
    }

    /**
     * 在绘制页面前先加载数据
     */
    private void initData(){
        String title=getIntent().getExtras().getString("title");
    }
    private void initDialog(View view) {
        Button btn_play = (Button) view.findViewById(R.id.btn_play);
        Button btn_pics = (Button) view.findViewById(R.id.btn_pics);
        Button btn_cncel = (Button) view.findViewById(R.id.btn_cncel);
        /**
         * 弹框点击事件
         *
         */

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //T.ss("拍照");
                openCamera();

            }
        });
        btn_pics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //T.ss("从相册中选择");
                openPhones();
            }
        });
        btn_cncel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //T.ss("取消");
                dialog.dismiss();
            }
        });
    }

    private void openPhones() {
        Intent intentFromGallery = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentFromGallery.setType("image/*"); // 设置文件类型
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery, 2);
    }

    private void openCamera() {
        // 打开相机
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用,存储缓存图片
        if (HavaSdCard.hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(IMGURL + IMAGE_FILE_NAME_TEMP)));
        }
        startActivityForResult(intentFromCapture, 3);
    }


    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 4);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case 2:// 打开相册返回
                if (data != null) {
                    startPhotoZoom(data.getData());
                }
                break;
            case 3:
                if (resultCode == -1) {
                    File tempFile = new File(IMGURL + IMAGE_FILE_NAME_TEMP);
                    startPhotoZoom(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(this, "获取相机图片失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case 4:// 裁剪完成,删除照相机缓存的图片
                final File tempFile = new File(IMGURL + IMAGE_FILE_NAME_TEMP);
                if (tempFile.exists()) {
                    new Thread() {
                        public void run() {
                            tempFile.delete();
                        }
                    }.start();
                }
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap photo = extras.getParcelable("data");
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] bytes = baos.toByteArray();
                        logoBase = Base64.encodeToString(bytes, Base64.DEFAULT);
                        img.setImageBitmap(photo);
                        imgs = logoBase;//当上传时可以上传img
                        picloade();

                    }
                }
                dialog.cancel();
                break;
//            case 5:
//                String phone = data.getExtras().getString("phone");
//                if (phone.equals("1")) {
//                    tv_phone.setText(phone);
//                } else if (phone.equals("2")) {
//                    tv_phone.setText(phones);
//                } else {
//                    tv_phone.setText(phone);
//                    phones = phone;
//                }
//                break;
//            case 6:
//                String email = data.getExtras().getString("email");
//
//                if (email.equals("1")) {
//                    tv_mail.setText(email);
//                } else if (email.equals("2")) {
//                    tv_mail.setText("");
//                } else {
//                    tv_mail.setText(email);
//                    emails = email;
//                }
//                break;
//            case 7:
//                String name = data.getExtras().getString("name");
//
//                if (name.equals("1")) {
//                    tv_ncname.setText(name);
//                } else if (name.equals("2")) {
//                    tv_ncname.setText("");
//                } else {
//                    tv_ncname.setText(name);
//                    ncname = name;
//                }
//                break;
//            case 8:
//                String nametrue = data.getExtras().getString("name");
//
//                if (nametrue.equals("1")) {
//                    tv_namet.setText(nametrue);
//                } else if (nametrue.equals("2")) {
//                    tv_namet.setText("");
//                } else {
//                    tv_namet.setText(nametrue);
//                    nametr = nametrue;
//                }
//                break;
            default:
                break;
        }
    }

    private void picloade() {

//        Map<String, String> map = new HashMap<String, String>();
//        map.put("pictures", img);// 头像
//        //L.e(img);
//        // map.put("uuid", Token.get(this));
//        Resources res = getResources();
//        String url = res.getString(R.string.app_service_url)
//                + "/app/member/editavatar/sign/aggregation/?uuid="+Token.get(this);
//        LReqEntity entity = new LReqEntity(url, map);
//        ActivityHandler handler = new ActivityHandler(this);
//        handler.startLoadingData(entity, 1);

    }
    private void getJsonData(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            int code = jsonObject.getInt("status");
            if (code == 1) {
                //T.ss("图片已上传");
//                T.ss(jsonObject.getString("info"));
            } else {
//                T.ss(jsonObject.getString("info"));
                String longs = jsonObject.getString("info");
//                if (longs.equals("请先登录")) {
//                    Intent intent = new Intent(this, LoginMain.class);
//                    startActivity(intent);
//                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getJsoninf(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            int code = jsonObject.getInt("status");
            if (code == 1) {
                JSONObject jsonO = jsonObject.getJSONObject("list");

//                id: 1
//                username: 叶子
//                truename: 唯一
//                sex: 1
//                email: 123456@qq.com
//                mobile: 13543456789
//                birthday: 2010
//                head_portrait: Uploads/appavatar1448242206.jpg
                if (jsonO.length() > 0) {

//                    String id = jsonO.getString("id");
//                    String username = jsonO.getString("username");
//                    if (!username.equals(null)) {
//                        ncname = username;
//                    } else {
//                        ncname = "";
//                    }
//                    tv_ncname.setText(ncname);
//                    String truename = jsonO.getString("truename");
//                    if (!truename.equals(null)) {
//                        nametr = truename;
//                    } else {
//                        nametr = "";
//                    }
//                    tv_namet.setText(nametr);
//                    String sexs = jsonO.getString("sex");
//                    if (!sexs.equals(null)) {
//                        if (sexs.equals("1")) {
//                            sex = "男";
//                        } else if (sexs.equals("2")) {
//                            sex = "女";
//                        }
//                    } else {
//                        sex = "";
//                    }
//                    tv_sex.setText(sex);
//                    String email = jsonO.getString("email");
//                    emails = email;
//                    tv_mail.setText(emails);
//                    String mobile = jsonO.getString("mobile");
//                    phones = mobile;
//                    tv_phone.setText(phones);
//                    String birthday = jsonO.getString("birthday");
//                    tv_brithday.setText(birthday);
//                    String vimg=jsonO.getString("head_portrait");
//                    img=vimg;
                }
                ImageLoader.getInstance().displayImage("",img,options);

            } else {
//                T.ss(jsonObject.getString("info"));
                //String longs = jsonObject.getString("info");
//                if (longs.equals("请先登录")) {
//                    Intent intent = new Intent(this, LoginMain.class);
//                    startActivity(intent);
//                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showBuyDialog() {
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.Dialog_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.onWindowAttributesChanged(wl);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.setting_img){
            showBuyDialog();
        }
//        tv_name=(TextView)findViewById(R.id.tv_n);
//        tv_nc=(TextView)findViewById(R.id.tv_tne);
//        tv_email=(TextView)findViewById(R.id.tv_email);
//        tv_sex=(TextView)findViewById(R.id.tv_sex);

        if(id==R.id.tv_n){
            //名字
        }
        if(id==R.id.tv_tne){
            //昵称
        }
        if(id==R.id.tv_email){
            //邮箱
        }
        if(id==R.id.tv_sex){
            //性别
        }
    }


    // 性别选择
    public void dialogShow() {
        listView = new ListView(this);
        float scale = getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (8 * scale + 0.5f);
        listView.setPadding(0, dpAsPixels, 0, dpAsPixels);
        listView.setDividerHeight(0);
        listView.setAdapter(dialogAda);
        listView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        initClick();
        materialDialog = new MaterialDialog(this).setTitle("请选择")
                .setContentView(listView);
        materialDialog.show();
    }

    //性别选择填充数据
    public void setGender() {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "男");
        gender_list.add(map);
        map = new HashMap<>();
        map.put("id", "2");
        map.put("name", "女");
        gender_list.add(map);
        // gender_list.add("取消");
    }

    //性别选择的点击事件
    private void initClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (position == 0) {
                    sex = gender_list.get(position).get("name");
                    sexid = gender_list.get(position).get("id");
                    tv_sex.setText(sex);
                    // T.ss(sexid);
                    sex();
                }
                if (position == 1) {
                    sex = gender_list.get(position).get("name");
                    sexid = gender_list.get(position).get("id");
                    tv_sex.setText(sex);
                    //T.ss(sexid);
                    sex();
                }

                materialDialog.dismiss();

            }
        });


    }
    //性别
    private void sex() {
        if (!sexid.equals(null)) {
//            Map<String, String> map = new HashMap<>();
//            map.put("sex", sexid.trim());// 性别
//            Resources res = getResources();
//            String url = res.getString(R.string.app_service_url)
//                    + "/app/member/editsex/sign/aggregation/?uuid="+Token.get(this);
//            LReqEntity entity = new LReqEntity(url, map);
//            ActivityHandler handler = new ActivityHandler(this);
//            handler.startLoadingData(entity, 2);
        }

    }

}
