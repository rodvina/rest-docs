package com.kemper.docs.cms;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.kemper.docs.rest.model.DecRequest;
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
		request.setFromDate("01012017");
		request.setToDate("06012017");
		request.getProducerCodeList().add("419600");
		request.getProducerCodeList().add("419601");
		

		Expression actual = service.getExpression(request);
		
		Expression expected = Expression.eq("FROM_DT", request.getFromDate())
								.and(Expression.eq("TO_DATE", request.getToDate())
								.and(Expression.in("PRODUCER_CODE", request.getProducerCodeList().toArray(new String[request.getProducerCodeList().size()]))));
		System.out.print(actual);
		assertThat(actual.toString(), equalTo(expected.toString()));
	}

}
