/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dz.apigw.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Administrator
 */
@Configuration
@ConfigurationProperties(prefix="apigw")
public class AppProperties {
    private User user = new User();
    private Token token = new Token();
    private Query query = new Query();

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
    
    public class User {
        private String username;
        private String password;
        private String ipaddress;
        private String cp_code;
        private String service_code;
        private String service_id;
        private String package_code;
        private String type_of_api;
        private int time_out;
        private int db_query_inteval;
        private String api_account_checking;
        private String api_paymentnotification;
        private String api_paymentnotification_test;
        
        
        public String getApi_paymentnotification_test() {
			return api_paymentnotification_test;
		}

		public void setApi_paymentnotification_test(String api_paymentnotification_test) {
			this.api_paymentnotification_test = api_paymentnotification_test;
		}

		public String getApi_account_checking() {
			return api_account_checking;
		}

		public void setApi_account_checking(String api_account_checking) {
			this.api_account_checking = api_account_checking;
		}

		public String getApi_paymentnotification() {
			return api_paymentnotification;
		}

		public void setApi_paymentnotification(String api_paymentnotification) {
			this.api_paymentnotification = api_paymentnotification;
		}

        public int getTime_out() {
			return time_out;
		}

		public void setTime_out(int time_out) {
			this.time_out = time_out;
		}

		public int getDb_query_inteval() {
			return db_query_inteval;
		}

		public void setDb_query_inteval(int db_query_inteval) {
			this.db_query_inteval = db_query_inteval;
		}

		public String getType_of_api() {
			return type_of_api;
		}

		public void setType_of_api(String type_of_api) {
			this.type_of_api = type_of_api;
		}

		public String getCp_code() {
			return cp_code;
		}

		public void setCp_code(String cp_code) {
			this.cp_code = cp_code;
		}

		public String getService_code() {
			return service_code;
		}

		public void setService_code(String service_code) {
			this.service_code = service_code;
		}

		public String getService_id() {
			return service_id;
		}

		public void setService_id(String service_id) {
			this.service_id = service_id;
		}

		public String getPackage_code() {
			return package_code;
		}

		public void setPackage_code(String package_code) {
			this.package_code = package_code;
		}

		public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setIpaddress(String ipaddress) {
            this.ipaddress = ipaddress;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getIpaddress() {
            return ipaddress;
        }
    }    
    
    public class Token {
        private String prefix;
        private String expiration_time;
        private String secret;
        private String header_string;

        public String getPrefix() {
            return prefix;
        }

        public String getExpiration_time() {
            return expiration_time;
        }

        public String getSecret() {
            return secret;
        }

        public String getHeader_string() {
            return header_string;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public void setExpiration_time(String expiration_time) {
            this.expiration_time = expiration_time;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public void setHeader_string(String header_string) {
            this.header_string = header_string;
        }
    }   
    

    public class Query {
        private int maxrow;
        private int maxrow_offset;

        public int getMaxrow() {
            return maxrow;
        }

        public void setMaxrow(int maxrow) {
            this.maxrow = maxrow;
        }

        public int getMaxrow_offset() {
            return maxrow_offset;
        }

        public void setMaxrow_offset(int maxrow_offset) {
            this.maxrow_offset = maxrow_offset;
        }

        
    }    
    
}
