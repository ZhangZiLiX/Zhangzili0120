package com.bwie.zhangzili20190120.shoppage;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.zhangzili20190120.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * date:2019/1/20
 * author:张自力(DELL)
 * function:  商品的适配器
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    private ShopBean.DataBean mDataBean;

    //接口回调
    public interface setShopOnClickListener{
        //点击跳转
        void setPid(int pid);
    }
    private setShopOnClickListener mSetShopOnClickListener;
    public void setSetShopOnClickListener(setShopOnClickListener listener){
        mSetShopOnClickListener = listener;
    }



    private Context mContext;
    private List<ShopBean.DataBean> mDataBeanList;

    public ShopAdapter(Context context, List<ShopBean.DataBean> dataBeanList) {
        mContext = context;
        mDataBeanList = dataBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // View view = View.inflate(mContext, R.layout.shop_adapter,null);
        View view = LayoutInflater.from(mContext).inflate(R.layout.shop_adapter,null);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        if(mDataBeanList.size()!=0){
            mDataBean = mDataBeanList.get(position);
            if(!mDataBean.equals("")){
                Glide.with(mContext).load(mDataBean.getImages().replace("https","http")).into(holder.mImgShop);

                String images = mDataBean.getImages();
                String[] split = images.split("\\|");

                Uri uri = Uri.parse(split[0].replace("https","http"));
                holder.mSimpleShop.setImageURI(uri);

                holder.mTxtShopTitle.setText(mDataBean.getTitle());
                holder.mTxtShopPrice.setText(mDataBean.getPrice()+"");
                holder.mTxtShopPing.setText(mDataBean.getPscid()+"%好评");
                Glide.with(mContext).load(mDataBean.getImages().replace("https","http")).into(holder.mImgShop);
            }
        }

        //条目点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSetShopOnClickListener.setPid(mDataBean.getPid());
            }
        });

        //长按事件
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mContext,"成功删除"+position+"号商品",Toast.LENGTH_SHORT).show();
                //进行删除
                mDataBeanList.remove(position);
                notifyDataSetChanged();
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        if(mDataBeanList.size()==0){
            return 0;
        }
        return mDataBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView mSimpleShop;
        private final TextView mTxtShopTitle;
        private final TextView mTxtShopPrice;
        private final TextView mTxtShopPing;
        private final ImageView mImgShop;

        public ViewHolder(View itemView) {
            super(itemView);
            mImgShop = itemView.findViewById(R.id.img_shop);
            mSimpleShop = itemView.findViewById(R.id.simple_shop);
            mTxtShopTitle = itemView.findViewById(R.id.txt_shop_title);
            mTxtShopPrice = itemView.findViewById(R.id.txt_shop_price);
            mTxtShopPing = itemView.findViewById(R.id.txt_shop_ping);

        }
    }

}
