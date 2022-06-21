package com.bootcamp.incomeproductservice.util;

import com.bootcamp.incomeproductservice.model.Client;
import com.bootcamp.incomeproductservice.model.Credit;
import com.bootcamp.incomeproductservice.model.CreditCard;
import com.bootcamp.incomeproductservice.model.dto.ClientDto;
import com.bootcamp.incomeproductservice.model.dto.CreditCardDto;
import com.bootcamp.incomeproductservice.model.dto.CreditDto;

public interface DtoConverter {
  Credit convert(CreditDto creditDto);

  CreditDto convert(Credit credit);

  CreditCard convert(CreditCardDto creditCardDto);

  CreditCardDto convert(CreditCard creditCard);

  Client convert(ClientDto clientDto);

  ClientDto convert(Client client);

}
