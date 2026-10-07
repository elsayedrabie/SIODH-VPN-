/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package com.socksfast.sockshttp.servers;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.socksfast.sockshttp.servers.ServersAdapter;
import com.socksfast.sockshttp.servers.ServersModel;
import com.socksfast.vpn.R;
import java.io.InputStream;

public class ServersViewHolder extends RecyclerView.ViewHolder{
    private TextView servername;
    private TextView serverinfo;
    private ImageView servericon;
    private ImageView serversignal;
    private Context context;
    private ServersModel modelo;
    
    public ServersViewHolder(@NonNull View view, ServersAdapter.onItemClickListener listener) {
        super(view);
        context = view.getContext();
        servername = view.findViewById(R.id.servers_name);
        serverinfo = view.findViewById(R.id.servers_info);
        servericon = view.findViewById(R.id.servers_image);
        
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(modelo.getServerPosicion());
            }
        });
    }
    
    public void bind(ServersModel server) {
        modelo = server;
        servername.setText(server.getServerName());
        serverinfo.setText(server.getServerInfo());
        try { 
            setImagen(servericon, server.getServerFlag());
        } catch (Exception e) {
            servericon.setImageResource(R.drawable.icon);
        }
    }
    
    
    public void setImagen(ImageView im, String nameo) throws Exception {
        InputStream inputStream = context.getAssets().open("flags/" + nameo + ".png");
		im.setImageDrawable(Drawable.createFromStream(inputStream, nameo + ".png"));
	}
    
}
