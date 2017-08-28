package com.kemper.docs.rest.cms;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.kemper.docs.rest.cms.DecsCMSDocumentService;
import com.kemper.docs.rest.model.DecRequest;
import com.kemper.docs.rest.util.DateUtil;
import com.ksg.cms.client.model.Expression;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class DecsCMSDocumentServiceTest {
	
	DecsCMSDocumentService service;

    @Before
    public void setUp() {

        service = new DecsCMSDocumentService();
    }
    
	@Test
	public void testGetExpressionDecRequest() {
		//given
		DecRequest request = new DecRequest();
		request.setFromDate(LocalDate.of(2017, 01, 01));
		request.setToDate(LocalDate.of(2017, 06, 12));
		request.getProducerCodeList().add("419600");
		request.getProducerCodeList().add("419601");
		

		Expression actual = service.getExpression(request);
		
		Expression expected = Expression.between("POST_DT", DateUtil.toDate(request.getFromDate()), DateUtil.toDate(request.getToDate()))
								.and(Expression.in("PRDCR_CD", request.getProducerCodeList().toArray(new String[request.getProducerCodeList().size()])));
		System.out.print(actual);
		assertThat(actual.toString(), equalTo(expected.toString()));
	}

}
