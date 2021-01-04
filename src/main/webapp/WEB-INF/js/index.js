$(function () {
    // $('#one').css("background","#bbffaa")
    // $('.mini').css("background","#bbffaa")
    // $('div').css("background","#bbffaa")
    // $('body div').css("background","#bbffaa")
    // $('body > div').css("background","#bbffaa")
    // $('.one + div').css("background","#bbffaa")
    // $('#two ~ div').css("background","#bbffaa")
    // $('#two').nextAll('div').css("background","#bbffaa")
    // $('#two').next('div').css("background","#bbffaa")
    // $('#two').siblings('div').css("background","#bbffaa")

    $('#panel h5.head').bind("click",function () {
        console.log("head")
        var $content = $(this).next("div.content");

            $content.hide(1000);
    })

    $('#add').click(function () {
        var $option1 = $('#select1 option:selected');
        $option1.appendTo('#select2');
    })

})