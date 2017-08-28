package com.kemper.docs.rest.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kemper.docs.rest.model.DecRequest;
import com.kemper.docs.rest.model.DecResult;
import com.kemper.docs.rest.model.SearchResults;
import com.kemper.docs.rest.service.DocsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value="/api/v1/docs/dec")
@Api(value="kemper docs", produces = "application/json")
public class DecsController {
	
	@Autowired
	@Qualifier("DecsService")
	private DocsService<DecRequest, DecResult> service;

	@ApiOperation(value="findDecsBySearchParams", notes="Get a list of declaration documents based on search criteria", produces="application/json")
	@GetMapping
	public ResponseEntity<SearchResults<DecResult>> findDecsBySearchParams(
			@ApiParam(value="From date (yyyyMMdd)", required=true) @RequestParam @DateTimeFormat(pattern="yyyyMMdd") LocalDate fromDate, 
			@ApiParam(value="To date (yyyyMMdd)", required=true) @RequestParam  @DateTimeFormat(pattern="yyyyMMdd") LocalDate toDate, 
			@ApiParam(value="Last name") @RequestParam(required=false, name="lname") String lastName,
			@ApiParam(value="Policy number") @RequestParam(required=false) String polno, 
			@ApiParam(value="Transaction type") @RequestParam(required=false) String tranType) {
		
		return new ResponseEntity<SearchResults<DecResult>>(this.buildSearchResults(fromDate, toDate, lastName, polno, tranType), HttpStatus.OK);
	}

	private SearchResults<DecResult> buildSearchResults(LocalDate fromDate, LocalDate toDate, String lastName,
			String polno, String tranType) {
		
		SearchResults<DecResult> results = new SearchResults<DecResult>();
		DecResult dec = new DecResult();
		dec.setPolicyno(polno);
		dec.setTransactionType(tranType);
		dec.setName(lastName);
		dec.setEffectiveDate(fromDate);
		dec.setProcessedDate(toDate);
		results.getResults().add(dec);
		return results;
	}
}
