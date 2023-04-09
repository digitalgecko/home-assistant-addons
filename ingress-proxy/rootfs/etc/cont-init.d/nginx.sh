#!/command/with-contenv bashio

DEST_SABNZBD_URL=$(bashio::config 'dest_sabnzbd_url')
DEST_SABNZBD_PART=$(bashio::config 'dest_sabnzbd_part')

DEST_RADARR_URL=$(bashio::config 'dest_radarr_url')
DEST_RADARR_PART=$(bashio::config 'dest_radarr_part')

DEST_SONARR_URL=$(bashio::config 'dest_sonarr_url')
DEST_SONARR_PART=$(bashio::config 'dest_sonarr_part')

DESTINATION=$(bashio::config 'destination')

bashio::log.info "Destination: ${DESTINATION}"

bashio::var.json \
    destination "$(bashio::config 'destination')" \
    | tempio \
        -template /etc/nginx/templates/direct.gtpl \
        -out /etc/nginx/servers/direct.conf
