

$(document).ready(function() {

    $(".datepicker").datepicker('setDate', null);


window.selectedProductId=0;
window.selectedCustomerId=0;

    $.getJSON("http://localhost:8080/json/products", function(data) {
        $.each(data, function(index, item) {
            $('#products').append(
                $('<option></option>').val(data[index].id).html(data[index].name)
            );


        });
    });

    $.getJSON("http://localhost:8080/json/customers", function(data) {
        $.each(data, function(index, item) {
            console.log("customer data[index].name " + data[index].name);
            console.log("customer this.item " + this.item);
            $('#customers').append(
                $('<option></option>').val(data[index].id).html(data[index].name)
            );
        });
    });



    $('#products').change(function() {

        var selectedVal = $(this).find(':selected').val();

        $('#discount').empty();
        $('#coupons').empty();
        selectedProductId=selectedVal;


        //fetch coupons (using customer id) and populate #coupons

//  console.log("on change of products select selected customer id is "+selectedCustomerId);
if(selectedCustomerId!=0){
        $.getJSON("http://localhost:8080/json/customers/" + selectedCustomerId + "/coupons/"+ selectedProductId+"/", function(data) {

                    $.each(data, function(index, item) {

                        $('#coupons').append(
                            $('<option></option>').val(data[index].id).html(data[index].name)
                        );
                    });
                });
}
        //fetch discount for product and populate "list"
        $.getJSON("http://localhost:8080/json/products/" + selectedVal + "/discount", function(data) {

            $('#discount').append(
                $('<option></option>').val(data.id).html(data.percentOffValue)
            );


        });
    });




    $('#customers').change(function() {

        var selectedVal = $(this).find(':selected').val();

        $('#coupons').empty();
     //   console.log("on change of customers select customer id is "+selectedCustomerId);
        selectedCustomerId=selectedVal;

        $.getJSON("http://localhost:8080/json/customers/" + selectedCustomerId + "/coupons/"+ selectedProductId+"/", function(data) {

            $.each(data, function(index, item) {

                $('#coupons').append(
                    $('<option></option>').val(data[index].id).html(data[index].name)
                );
            });
        });

    });


$("#purchaseButton").click(function(){

    var var1= document.getElementById("products").value+" "+document.getElementById("discount").value+" "+document.getElementById("customers").value+" "+document.getElementById("coupons").value+" "+
    document.getElementById("amount").value+" "+document.getElementById("date").value;


    $.ajax({

            type:"POST",
            url:'http://localhost:8080/newpurchase',

            data:var1,
            //can send multipledata like {data1:var1,data2:var2,data3:var3
            //can use dataType:'text/html' or 'json' if response type expected
            success:function(responsedata){
                   // process on data
                   alert("got response as "+"'"+responsedata+"'");

            }
         })


});

});