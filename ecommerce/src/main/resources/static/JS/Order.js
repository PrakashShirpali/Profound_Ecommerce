let paymetProcessingDetail={
	"pincode":null,
	"city":null,
	'addressId':null,
	"paymentMode":null,
	"bankDetails":{
		"upiId":null,
		"bankName":null,
		"accountNumber":null,
		"cardNumber":null,
		"cardExpirydate":null,
		"cashOnDelivery":null
	}
	};
	
	
// Function to call the API using fetch

function saveBankProcessingDetails(event) {
	
	    event.preventDefault(); // Prevent default form submission
    // Send a POST request with paymentProcessingDetail as the request body
    fetch('saveBankProcessingDetails', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(paymetProcessingDetail)
    })
    .then(function(response) {
		console.log(response);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(function(data) {
        // Handle success response
        console.log('Success:', data);
    })
    .catch(function(error) {
        // Handle error response
        console.error('Error:', error);
    });
}



function handlerAddress(user) {
	paymetProcessingDetail.country = user.value;
	 paymetProcessingDetail.state = user.value;
	paymetProcessingDetail.city = user.value;
	paymetProcessingDetail.pincode = user.value;
    paymetProcessingDetail.addressId = user.value;
    console.log("Selected address:", paymetProcessingDetail);
}

function handlerPayment(selectedRadio) {
     paymetProcessingDetail.paymentMode=selectedRadio.value;
     paymetProcessingDetail.paymentMode.upiId=document.getElementById("upiId").value;
    console.log("Selected payment method:", paymetProcessingDetail);
    handlerPaymentOption(paymetProcessingDetail.paymentMode);
}

/*   function handlerAddress(radio) {
        var selectedAddressIndex = radio.parentNode.getAttribute('data-index'); // Assuming you set data-index in your HTML
        var selectedAddress = user.addresses[selectedAddressIndex];
        var addressObject = {
            pincode: selectedAddress.getPincode(),
            city: selectedAddress.getCity(),
            state: selectedAddress.getState(),
            country: selectedAddress.getCountry()
        };
        console.log(addressObject); // You can use this object as needed
    }*/
    
  function handlerPaymentOption(option) {
        let upiFields = document.getElementById('upiFields');
        let onlineBankingFields = document.getElementById('onlineBankingFields');
        let creditCardFields = document.getElementById('creditCardFields');

        switch (option) {
            case 'upi':
                upiFields.style.display = 'block';
                onlineBankingFields.style.display = 'none';
                creditCardFields.style.display = 'none';
                break;
            case 'onlineBanking':
                upiFields.style.display = 'none';
                onlineBankingFields.style.display = 'block';
                creditCardFields.style.display = 'none';
                break;
            case 'creditCard':
                upiFields.style.display = 'none';
                onlineBankingFields.style.display = 'none';
                creditCardFields.style.display = 'block';
                break;
            case 'cashOnDelivery':
                upiFields.style.display = 'none';
                onlineBankingFields.style.display = 'none';
                creditCardFields.style.display = 'none';
                break;
            default:
                upiFields.style.display = 'none';
                onlineBankingFields.style.display = 'none';
                creditCardFields.style.display = 'none';
                break;
        }
    }
    
 function  handlerUpiId()
    {
		let upiId=document.getElementById("upiId").value;
		paymetProcessingDetail.bankDetails.upiId=upiId;
		console.log(paymetProcessingDetail);
	}
	
function  handlerOnlineBanking()
    {
		let bankName=document.getElementById("bankName").value;
		paymetProcessingDetail.bankDetails.bankName=bankName;
		let accountNumber=document.getElementById("accountNumber").value;
		paymetProcessingDetail.bankDetails.accountNumber=accountNumber;
		console.log(paymetProcessingDetail);
	}
	
function  handlerCreditCard()
    {
	let creditCardNumber=document.getElementById("cardNumber").value;
		paymetProcessingDetail.bankDetails.cardNumber=creditCardNumber;
		let expirydate=document.getElementById("cardExpiryDate").value;
		paymetProcessingDetail.bankDetails.cardExpirydate=expirydate;
		console.log(paymetProcessingDetail);	
	}
	
function  handlerCashOnDelivery()
    {
		let cod=document.getElementById("cashOnDelivery").value;
		paymetProcessingDetail.bankDetails.cashOnDelivery=cod;
		console.log(paymetProcessingDetail);
	}
	
	
function reviewBankDetails()
{
	let innerhtml=`<div>
	
	<p>Address ID : ${paymetProcessingDetail.addressId}</p>
	<p>City : ${paymetProcessingDetail.city}</p>
	<p>Pincode: ${paymetProcessingDetail.pincode}</p>
	<p>Payment Mode : ${paymetProcessingDetail.paymentMode}</p>
	<p>Account Number : ${paymetProcessingDetail.bankDetails.accountNumber}</p>	
	<p>Bank Name : ${paymetProcessingDetail.bankDetails.bankName}</p>			
	<p>Card Expiry Date : ${paymetProcessingDetail.bankDetails.cardExpirydate}</p>			
	<p>Card Number : ${paymetProcessingDetail.bankDetails.cardNumber}</p>			
	<p>COD : ${paymetProcessingDetail.bankDetails.cashOnDelivery}</p>			
	<p>UPI ID : ${paymetProcessingDetail.bankDetails.upiId}</p>			
	</div>`
	document.getElementById("bankDetail").innerHTML=innerhtml;
	console.log(document.getElementById("bankDetail"));
	console.log("reviewBankDetails");
}