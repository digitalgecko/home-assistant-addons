server {
    listen 8099 default_server;

    include /etc/nginx/includes/server_params.conf;

    location / {
        absolute_redirect off;

        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header Origin "";
        proxy_pass {{ .dest_sabnzbd_url }};
        proxy_redirect '/' $http_x_ingress_path/;
        sub_filter 'href="/' 'href="$http_x_ingress_path/';
        sub_filter '<script src="/' '<script src="$http_x_ingress_path/';
        sub_filter "top.location.href='" "top.location.href='$http_x_ingress_path";

        sub_filter_once off;
    }


    location /{{ .dest_sabnzbd_part }}/ {
        absolute_redirect off;

        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header Origin "";
        proxy_pass {{ .dest_sabnzbd_url }};
        proxy_redirect '/' $http_x_ingress_path/;
        sub_filter 'href="/' 'href="$http_x_ingress_path/';
        sub_filter '<script src="/' '<script src="$http_x_ingress_path/';
        sub_filter "top.location.href='" "top.location.href='$http_x_ingress_path";

        sub_filter_once off;
    }

        location /{{ .dest_radarr_part }}/ {
            absolute_redirect off;

            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header Origin "";
            proxy_pass {{ .dest_radarr_url }};
            proxy_redirect '/' $http_x_ingress_path/;
            sub_filter 'href="/' 'href="$http_x_ingress_path/';
            sub_filter '<script src="/' '<script src="$http_x_ingress_path/';
            sub_filter "top.location.href='" "top.location.href='$http_x_ingress_path";

            sub_filter_once off;
        }

            location /{{ .dest_sonarr_part }}/ {
                absolute_redirect off;

                proxy_set_header Host $host;
                proxy_set_header X-Real-IP $remote_addr;
                proxy_set_header Origin "";
                proxy_pass {{ .dest_sonarr_url }};
                proxy_redirect '/' $http_x_ingress_path/;
                sub_filter 'href="/' 'href="$http_x_ingress_path/';
                sub_filter '<script src="/' '<script src="$http_x_ingress_path/';
                sub_filter "top.location.href='" "top.location.href='$http_x_ingress_path";

                sub_filter_once off;
            }
}
