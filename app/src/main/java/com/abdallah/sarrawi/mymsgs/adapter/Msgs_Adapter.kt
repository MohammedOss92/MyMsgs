package com.abdallah.sarrawi.mymsgs.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.os.Build
import android.text.ClipboardManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.abdallah.sarrawi.mymsgs.models.MsgModelWithTitle
import com.abdallah.sarrawi.mymsgs.R
import com.abdallah.sarrawi.mymsgs.Utils
import com.abdallah.sarrawi.mymsgs.databinding.MsgsDesignBinding
import com.abdallah.sarrawi.mymsgs.ui.fragments.*
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback


class Msgs_Adapter(val con:Context,val frag:Fragment /*,var callBack: CallBack*/) : RecyclerView.Adapter<Msgs_Adapter.MyViewHolder>() {

    var onItemClick: ((item:MsgModelWithTitle,position:Int) -> Unit)? = null
    var onClick: ((Unit) -> Unit)? = null
    private var adCount = 4
    private var shareCount = 0
    var mInterstitialAd: InterstitialAd?=null

    @SuppressLint("NotifyDataSetChanged")
    inner class MyViewHolder(val binding: MsgsDesignBinding) : RecyclerView.ViewHolder(binding.root) {
        var adView: AdView ?=null
        init {



            binding.moreBtn.setOnClickListener{popupMenus(it)}
//            adView= itemView.findViewById(R.id.adView)

        }


        fun bind(position: Int) {
            val current_msgsModel = msgsModel[position]

            binding.apply {
                tvTitle.text=current_msgsModel.typeTitle
                tvMsgM.text = current_msgsModel.msgModel?.MessageName
                newMsgM.setImageResource(R.drawable.new_msg)
                if (current_msgsModel.msgModel?.new_msgs == 0) {
                    newMsgM.setVisibility(View.INVISIBLE)
                } else {
                    newMsgM.setVisibility(View.VISIBLE)
                }
                // check if the item is favorite or not
                if (current_msgsModel.msgModel!!.is_fav) {
                    favBtn.setImageResource(R.drawable.baseline_favorite_true)
                } else {
                    favBtn.setImageResource(R.drawable.baseline_favorite_border_false)
                }
            }


            binding.favBtn.setOnClickListener {

                onItemClick?.invoke(msgsModel[position], position)
                notifyItemChanged(adapterPosition)
            }
        }

         fun popupMenus(view: View) {

            val popupMenu = PopupMenu(con,view)
            popupMenu.inflate(R.menu.menu_msg)

            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.share ->{
//                        shareCount++
//
//
//                        if (shareCount >= 1) {
//// بمجرد أن يصل clickCount إلى 4، اعرض الإعلان
//                            if (mInterstitialAd != null) {
//                                mInterstitialAd?.show(con as Activity)
//                            } else {
//                                Log.d("TAG", "The interstitial ad wasn't ready yet.")
//                            }
//                            shareCount = 0 // اعيد قيمة المتغير clickCount إلى الصفر بعد عرض الإعلان
//
//
//                        }
                        Utils.IntenteShare(
                            con,
                            "مسجاتي",
                            "مسجاتي",
                            binding.tvMsgM.text.toString()
                        )
                        true
                    }
                    R.id.copy ->{

//                        shareCount++
//
//
//                        if (shareCount >= 1) {
//// بمجرد أن يصل clickCount إلى 4، اعرض الإعلان
//                            if (mInterstitialAd != null) {
//                                mInterstitialAd?.show(con as Activity)
//                            } else {
//                                Log.d("TAG", "The interstitial ad wasn't ready yet.")
//                            }
//                            shareCount = 0 // اعيد قيمة المتغير clickCount إلى الصفر بعد عرض الإعلان
//
//
//                        }
                        val stringYouExtracted: String = binding.tvMsgM.text.toString()

                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                            val clipboard =
                                con.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            clipboard.text = stringYouExtracted
                        } else {
                            val clipboard =
                                con.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                            val clip = ClipData
                                .newPlainText(stringYouExtracted, stringYouExtracted)
                            clipboard.setPrimaryClip(clip)
                        }
                        Toast.makeText(con, "تم نسخ النص", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.edit ->{
//                        shareCount++
//
//
//                        if (shareCount >= 1) {
//// بمجرد أن يصل clickCount إلى 4، اعرض الإعلان
//                            if (mInterstitialAd != null) {
//                                mInterstitialAd?.show(con as Activity)
//                            } else {
//                                Log.d("TAG", "The interstitial ad wasn't ready yet.")
//                            }
//                            shareCount = 0 // اعيد قيمة المتغير clickCount إلى الصفر بعد عرض الإعلان
//
//
//                        }
                        Toast.makeText(con, "edit", Toast.LENGTH_SHORT).show()

                        val direction = SecondFragmentDirections.actionSecondFragmentToEditFragment(
                            binding.tvMsgM.text.toString()
                        )

                        findNavController(frag).navigate(direction)


                        true
                    }

                    R.id.edit ->{

//                        shareCount++
//
//
//                        if (shareCount >= 1) {
//// بمجرد أن يصل clickCount إلى 4، اعرض الإعلان
//                            if (mInterstitialAd != null) {
//                                mInterstitialAd?.show(con as Activity)
//                            } else {
//                                Log.d("TAG", "The interstitial ad wasn't ready yet.")
//                            }
//                            shareCount = 0 // اعيد قيمة المتغير clickCount إلى الصفر بعد عرض الإعلان
//
//
//                        }
                        Toast.makeText(con, "edit", Toast.LENGTH_SHORT).show()

                        val direction = NewMsgsFragmentDirections.actionSecondFragmentToEditFragment(
                            binding.tvMsgM.text.toString()
                        )

                        findNavController(frag).navigate(direction)


                        true
                    }

                    else -> true
                }
            }
            popupMenu.show()
        }

    }



    private val diffCallback = object :DiffUtil.ItemCallback<MsgModelWithTitle>(){
        override fun areItemsTheSame(oldItem: MsgModelWithTitle, newItem: MsgModelWithTitle): Boolean {
            return oldItem.msgModel?.id == newItem.msgModel?.id
        }

        override fun areContentsTheSame(oldItem: MsgModelWithTitle, newItem: MsgModelWithTitle): Boolean {
            return newItem == oldItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var msgsModel: List<MsgModelWithTitle>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(MsgsDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.e("tessst","notifyyyy")
        holder.bind(position)

//        InterstitialAd_fun ()
//        if ((position + 1) % adCount == 0)
//        {  // تحقق مما إذا كانت هذه العنصر هي عنصر الإعلان
//            Log.d("AD_TAG", "Loading Ad at position $position")
//            holder.adView?.loadAd(AdRequest.Builder().build())  // تحميل الإعلان
//
//        }

//        val current_msgsModel = msgsModel[position]
//        holder.binding.apply {
////            tvTitleM.text=current_msgsModel.typeTitle.toString()
//            tvMsgM.text=current_msgsModel.msgModel?.MessageName
//            newMsgM.setImageResource(R.drawable.new_msg)
//
//            if (current_msgsModel.msgModel?.new_msgs == 0){
//
//                newMsgM.setVisibility(View.INVISIBLE)
//            }
//            else {
//                newMsgM.setVisibility(View.VISIBLE)
//            }
//
//            // check if the item is favorite or not
//            if (current_msgsModel.msgModel!!.is_fav){
//                favBtn.setImageResource(R.drawable.baseline_favorite_true)
//
//            }else{
//                favBtn.setImageResource(R.drawable.baseline_favorite_border_false)
//
//            }
//
//        }

//        int viewType = getItemViewType(position);
//    if (viewType == AD_TYPE) {
//        AdViewHolder adViewHolder = (AdViewHolder) viewHolder;
//        NativeExpressAdView adView = adViewHolder.adView;
//        AdRequest request = new AdRequest.Builder().build();
//        adView.loadAd(request);
//    } else {
//        //bind the regular view holder
//    }

//        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//    if (getItemViewType(position) == AD_TYPE) {
//        val adHolder = holder as AdViewHolder
//        val adView = adHolder.adView
//        val adRequest = AdRequest.Builder().build()
//        adView.loadAd(adRequest)
//    } else {
//        val myHolder = holder as MyViewHolder
//        myHolder.bind(position)
//    }
//}

//        if ((position + 1) % adCount == 0) {
//    holder.adView!!.visibility = View.VISIBLE
//    holder.msgBody.visibility = View.GONE
//    holder.adView?.loadAd(AdRequest.Builder().build())
//} else {
//    holder.msgBody.text = message.msg_body
//    holder.msgBody.visibility = View.VISIBLE
//    holder.adView!!.visibility = View.GONE
//}


    }
//    mInterstitialAd?.show(con as Activity)

    override fun getItemCount(): Int {
        return msgsModel.size
    }
    fun InterstitialAd_fun (){


        MobileAds.initialize(con) { initializationStatus ->
            // do nothing on initialization complete
        }

        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            con,
            "ca-app-pub-1895204889916566/9391166409",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    // The mInterstitialAd reference will be null until an ad is loaded.
                    mInterstitialAd = interstitialAd
                    Log.i("onAdLoadedL", "onAdLoaded")
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    // Handle the error
                    Log.d("onAdLoadedF", loadAdError.toString())
                    mInterstitialAd = null
                }
            }
        )
    }
}