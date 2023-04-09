server {
    listen 8099 default_server;

    include /etc/nginx/includes/server_params.conf;
index index.html;

    location / {
        error_page 418 = @sabnzb;
        error_page 419 = @sonarr;
        error_page 420 = @radarr;
        if ( $query_string = "service=sabnzbd" ) { return 418; }
        if ( $query_string = "service=sonarr" ) { return 419; }
        if ( $query_string = "service=radarr" ) { return 420; }

        root /www/;
    }


    location @sabnzb {
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

        location @radarr {
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

            location  @sonarr {
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
