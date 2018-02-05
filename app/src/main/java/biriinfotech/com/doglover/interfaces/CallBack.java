package biriinfotech.com.doglover.interfaces;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Biri Infotech on 2/4/2018.
 */

public interface CallBack<T> {

    abstract void onSuccess(Call<T> call, Response<T> response);
    abstract void onFailure(Call<T> call, Throwable t);

}
