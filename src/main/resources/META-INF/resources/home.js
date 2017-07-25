(function() {
    $(function() {
        $('#airQuality').bind('click', function() {
            $('#progressTrackingContent').css('display', 'none');
            $('#airQualityContent').css('display', 'block');
            $('#ctSiderbarUl').find('li.active').removeClass('active');
            $('#airQualityMap').parent().addClass('active');
        });
        $('#progressTracking').bind('click', function() {
            $('#airQualityContent').css('display', 'none');
            $('#progressTrackingContent').css('display', 'block');
        });
        $('#ctSiderbarUl').bind('click', function(e) {
            $(this).find('li.active').removeClass('active');
            $('#'+e.target.id).parent().addClass('active');
        });
    });
})();