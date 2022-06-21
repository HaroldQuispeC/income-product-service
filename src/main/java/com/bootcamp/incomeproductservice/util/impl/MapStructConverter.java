package com.bootcamp.incomeproductservice.util.impl;

import com.bootcamp.incomeproductservice.model.Client;
import com.bootcamp.incomeproductservice.model.Credit;
import com.bootcamp.incomeproductservice.model.CreditCard;
import com.bootcamp.incomeproductservice.model.dto.ClientDto;
import com.bootcamp.incomeproductservice.model.dto.CreditCardDto;
import com.bootcamp.incomeproductservice.model.dto.CreditDto;
import com.bootcamp.incomeproductservice.util.DtoConverter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapStructConverter extends DtoConverter {

  MapStructConverter MAPPER = Mappers.getMapper(MapStructConverter.class);

  @Override
  Credit convert(CreditDto creditDto);

  @Override
  CreditDto convert(Credit credit);

  @Override
  CreditCard convert(CreditCardDto creditCardDto);

  @Override
  CreditCardDto convert(CreditCard creditCard);

  @Override
  Client convert(ClientDto clientDto);

  @Override
  ClientDto convert(Client client);
}

