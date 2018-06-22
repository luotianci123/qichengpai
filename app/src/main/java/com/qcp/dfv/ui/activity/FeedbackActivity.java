package com.qcp.dfv.ui.activity;

import android.os.Bundle;

import com.qcp.dfv.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by panjunquan on 2018/3/4.
 * 意见反馈
 */

public class FeedbackActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        super.initAppBar();


        setTitle("意见反馈");
        showLeftView();

//        initPic();

    }

//    public void initPic() {
////        pics = new ArrayList<>();
////        ListCustomerBanner addpic = new ListCustomerBanner();
////        addpic.setIsadd(true);
////        pics.add(addpic);
////        adapter = new PicAdapter(FeedbackActivity.this, handler, 0);
////        GridView.setAdapter(adapter);
//    }
//
//    private Handler handler = new Handler() {
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 205:
//                    current = msg.arg1;
//                    startTakePhotoByPermissions();
//                    break;
//                case 206:
//                    current = msg.arg1;
////                    pics.remove(current);
////                    adapter.notifyDataSetChanged();
//                    break;
//                default:
//                    break;
//            }
//            super.handleMessage(msg);
//        }
//
//        ;
//    };
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
//
//    /**
//     * 请求数据
//     */
//    public void dataRequest() {
//        showWait(true);
//        File file = new File("123");
//        OkGo.post(UrlConstant.POST_MY_SETFEEDBACK)
//                .isMultipart(true)
//                .params("activephone", SharedPreferencesUtils.getPhone(this))
//                .params("contactWay", phone)
//                .params("questionView", content)
//                .addFileParams("picture1", fileOne)
//                .addFileParams("picture2", fileTwo)
//                .addFileParams("picture3", fileThree)
//                .execute(new JsonCallback<JsonObject>() {//ProductPageVoBean
//                    @Override
//                    public void onSuccess(JsonObject voBean, Call call, Response response) {
//                        showWait(false);
//                        if (voBean != null) {
//                            KLog.e("返回结果：" + voBean.toString());
//                            int code = voBean.get("code").getAsInt();
//                            String message = voBean.get("message").getAsString();
//                            AppUtils.showShortToast(message);
//                            if (code == 1) {
//                                finish();
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(String code, String message) {
//                        showWait(false);
//                        if (!TextUtils.isEmpty(message)) {
//                            AppUtils.showToast(message);
//                        }
//                    }
//                });
//    }
//
//    @OnClick(R.id.ok_bt)
//    public void onViewClicked() {
////        if (pics.size() == 2) {
////            fileOne.add(new File(pics.get(0).getImageFilePath()));
////        } else if (pics.size() == 3) {
////            fileOne.add(new File(pics.get(0).getImageFilePath()));
////            fileTwo.add(new File(pics.get(1).getImageFilePath()));
////        } else if (pics.size() == 4) {
////            fileOne.add(new File(pics.get(0).getImageFilePath()));
////            fileTwo.add(new File(pics.get(1).getImageFilePath()));
////            fileThree.add(new File(pics.get(2).getImageFilePath()));
////        }
//        content = contentEt.getText().toString();
//        phone = phoneEt.getText().toString();
//        if (!TextUtils.isEmpty(contentEt.getText().toString())) {
//            dataRequest();
//        } else {
//            AppUtils.showShortToast("内容不能为空");
//        }
//    }
//
//    private void startTakePhotoByPermissions() {
//        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
//        if (EasyPermissions.hasPermissions(this, perms)) {
//            try {
//                pickImage();
//            } catch (Exception e) {
//                Toast.makeText(this, "相机无法完成初始化,请正确授权", Toast.LENGTH_LONG).show();
//            }
//        } else {
//            EasyPermissions.requestPermissions(this, "请求获取相机权限", ImageMultiUtils.CAMERA_REQUEST_CODE, perms);
//        }
//    }
//
//    @Override
//    public void onPermissionsGranted(int requestCode, List<String> perms) {
//        try {
//            pickImage();
//        } catch (Exception e) {
//            Toast.makeText(this, "授权不正确,操作无法进行", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    @Override
//    public void onPermissionsDenied(int requestCode, List<String> perms) {
//        Toast.makeText(this, "授权不正确,操作无法进行", Toast.LENGTH_LONG).show();
//    }
//
//    //=========判断权限启动======================
///*===================使用multiimageselector选择图片===========================*/
//    private static final int REQUEST_IMAGE = 2;
//    int maxNum = 3;
//
//    public void pickImage() {
//        MultiImageSelector selector = MultiImageSelector.create(FeedbackActivity.this);
//        selector.showCamera(true);
//        selector.count(maxNum);
//        selector.multi();
//        selector.origin(mSelectPath);
//        selector.start(FeedbackActivity.this, REQUEST_IMAGE);
//    }
//
//    /*===================使用multiimageselector选择图片end===========================*/
//    private ArrayList<String> mSelectPath;
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode != 0) {
//            if (resultCode == RESULT_OK) {
////                pics.clear();
//                mSelectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
//                for (int i = 0; i < mSelectPath.size(); i++) {
//                    Log.e("error", mSelectPath.get(i));
//                    if (!mSelectPath.get(i).equals("edit")) {
////                        ListCustomerBanner addpic = new ListCustomerBanner();
////                        addpic.setBitmap(BitmapFactory.decodeFile(mSelectPath.get(i), ImageMultiUtils.getBitmapOption(3)));
////                        addpic.setImageFilePath(mSelectPath.get(i));
////                        addpic.setIsmodify(true);
////                        addpic.setIsadd(false);
////                        pics.add(addpic);
//                    }
//                }
////                if (pics.size() < 4) {
////                    ListCustomerBanner addpic = new ListCustomerBanner();
////                    addpic.setIsadd(true);
////                    pics.add(addpic);
////                }
////                adapter.notifyDataSetChanged();
//            }
//        }
//    }
}
