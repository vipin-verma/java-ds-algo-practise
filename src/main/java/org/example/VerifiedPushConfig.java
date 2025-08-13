package org.example;

import java.util.List;

public class VerifiedPushConfig {
    private double version;
    private List<Factor> factors;

    // Getters and Setters
    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public List<Factor> getFactors() {
        return factors;
    }

    public void setFactors(List<Factor> factors) {
        this.factors = factors;
    }

    public static class Factor {
        private int factor_id;
        private String factor_name;
        private String factor_impl_class;
        private ServerConfiguration server_configuration;
        private ServerDeviceContract server_device_contract;

        // Getters and Setters
        public int getFactor_id() {
            return factor_id;
        }

        public void setFactor_id(int factor_id) {
            this.factor_id = factor_id;
        }

        public String getFactor_name() {
            return factor_name;
        }

        public void setFactor_name(String factor_name) {
            this.factor_name = factor_name;
        }

        public String getFactor_impl_class() {
            return factor_impl_class;
        }

        public void setFactor_impl_class(String factor_impl_class) {
            this.factor_impl_class = factor_impl_class;
        }

        public ServerConfiguration getServer_configuration() {
            return server_configuration;
        }

        public void setServer_configuration(ServerConfiguration server_configuration) {
            this.server_configuration = server_configuration;
        }

        public ServerDeviceContract getServer_device_contract() {
            return server_device_contract;
        }

        public void setServer_device_contract(ServerDeviceContract server_device_contract) {
            this.server_device_contract = server_device_contract;
        }

        // Nested Classes
        public static class ServerConfiguration {
            private Message message;

            // Getters and Setters
            public Message getMessage() {
                return message;
            }

            public void setMessage(Message message) {
                this.message = message;
            }
        }

        public static class Message {
            private List<String> formats;
            private Variables variables;

            // Getters and Setters
            public List<String> getFormats() {
                return formats;
            }

            public void setFormats(List<String> formats) {
                this.formats = formats;
            }

            public Variables getVariables() {
                return variables;
            }

            public void setVariables(Variables variables) {
                this.variables = variables;
            }
        }

        public static class Variables {
            private List<String> verifiedPushNumberFactorServerMessage;

            // Getters and Setters
            public List<String> getVerifiedPushNumberFactorServerMessage() {
                return verifiedPushNumberFactorServerMessage;
            }

            public void setVerifiedPushNumberFactorServerMessage(List<String> verifiedPushNumberFactorServerMessage) {
                this.verifiedPushNumberFactorServerMessage = verifiedPushNumberFactorServerMessage;
            }
        }

        public static class ServerDeviceContract {
            private List<FactorAttribute> factor_attributes;

            // Getters and Setters
            public List<FactorAttribute> getFactor_attributes() {
                return factor_attributes;
            }

            public void setFactor_attributes(List<FactorAttribute> factor_attributes) {
                this.factor_attributes = factor_attributes;
            }
        }

        public static class FactorAttribute {
            private String attribute_name;
            private Object attribute_value;

            // Getters and Setters
            public String getAttribute_name() {
                return attribute_name;
            }

            public void setAttribute_name(String attribute_name) {
                this.attribute_name = attribute_name;
            }

            public Object getAttribute_value() {
                return attribute_value;
            }

            public void setAttribute_value(Object attribute_value) {
                this.attribute_value = attribute_value;
            }
        }
    }
}
