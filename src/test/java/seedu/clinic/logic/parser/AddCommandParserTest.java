package seedu.clinic.logic.parser;

//import static seedu.clinic.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static seedu.clinic.logic.commands.AddCommand.MESSAGE_SUPPLIER_MISSING_PREFIX;
import static seedu.clinic.logic.commands.CommandTestUtil.ADDRESS_DESC_WAREHOUSE_A;
import static seedu.clinic.logic.commands.CommandTestUtil.ADDRESS_DESC_WAREHOUSE_B;
import static seedu.clinic.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.clinic.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.clinic.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.clinic.logic.commands.CommandTestUtil.INVALID_NAME_DESC2;
import static seedu.clinic.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.clinic.logic.commands.CommandTestUtil.INVALID_REMARK_DESC;
import static seedu.clinic.logic.commands.CommandTestUtil.NAME_DESC_AMY2;
import static seedu.clinic.logic.commands.CommandTestUtil.NAME_DESC_BOB2;
import static seedu.clinic.logic.commands.CommandTestUtil.NAME_DESC_WAREHOUSE_A;
import static seedu.clinic.logic.commands.CommandTestUtil.NAME_DESC_WAREHOUSE_B;
import static seedu.clinic.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.clinic.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.clinic.logic.commands.CommandTestUtil.PHONE_DESC_WAREHOUSE_A;
import static seedu.clinic.logic.commands.CommandTestUtil.PHONE_DESC_WAREHOUSE_B;
import static seedu.clinic.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.clinic.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.clinic.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.clinic.logic.commands.CommandTestUtil.REMARK_DESC_BOB;
import static seedu.clinic.logic.commands.CommandTestUtil.REMARK_DESC_WAREHOUSE_A;
//import static seedu.clinic.logic.commands.CommandTestUtil.REMARK_DESC_WAREHOUSE_B;
import static seedu.clinic.logic.commands.CommandTestUtil.REMARK_DESC_WAREHOUSE_B;
import static seedu.clinic.logic.commands.CommandTestUtil.TYPE_DESC_SUPPLIER;
import static seedu.clinic.logic.commands.CommandTestUtil.TYPE_DESC_WAREHOUSE;
import static seedu.clinic.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.clinic.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.clinic.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.clinic.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
//import static seedu.clinic.logic.commands.CommandTestUtil.VALID_WAREHOUSE_ADDRESS_B;
import static seedu.clinic.logic.commands.CommandTestUtil.VALID_WAREHOUSE_NAME_A;
//import static seedu.clinic.logic.commands.CommandTestUtil.VALID_WAREHOUSE_NAME_B;
//import static seedu.clinic.logic.commands.CommandTestUtil.VALID_WAREHOUSE_NAME_B;
//import static seedu.clinic.logic.commands.CommandTestUtil.VALID_WAREHOUSE_PHONE_B;
//import static seedu.clinic.logic.commands.CommandTestUtil.VALID_WAREHOUSE_PRODUCT_NAME_B;
//import static seedu.clinic.logic.commands.CommandTestUtil.VALID_WAREHOUSE_PRODUCT_QUANTITY_B;
//import static seedu.clinic.logic.commands.CommandTestUtil.VALID_WAREHOUSE_REMARK_B;
import static seedu.clinic.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.clinic.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.clinic.testutil.TypicalSupplier.BOB;
import static seedu.clinic.testutil.TypicalWarehouse.B;

import org.junit.jupiter.api.Test;

import seedu.clinic.logic.commands.AddCommand;
import seedu.clinic.model.attribute.Email;
import seedu.clinic.model.attribute.Name;
import seedu.clinic.model.attribute.Phone;
import seedu.clinic.model.attribute.Remark;
import seedu.clinic.model.supplier.Supplier;
import seedu.clinic.model.warehouse.Warehouse;
import seedu.clinic.testutil.SupplierBuilder;
import seedu.clinic.testutil.WarehouseBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Supplier expectedSupplier = new SupplierBuilder(BOB).build();
        Warehouse expectedWarehouse = new WarehouseBuilder(B).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB2 + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + REMARK_DESC_BOB, new AddCommand(expectedSupplier));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_WAREHOUSE_B + PHONE_DESC_WAREHOUSE_B
                + ADDRESS_DESC_WAREHOUSE_B + REMARK_DESC_BOB, new AddCommand(expectedWarehouse));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY2 + NAME_DESC_BOB2 + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + EMAIL_DESC_BOB, new AddCommand(expectedSupplier));
        assertParseSuccess(parser, NAME_DESC_WAREHOUSE_A + NAME_DESC_WAREHOUSE_B + PHONE_DESC_WAREHOUSE_B
                + ADDRESS_DESC_WAREHOUSE_B + REMARK_DESC_WAREHOUSE_B, new AddCommand(expectedWarehouse));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB2 + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + REMARK_DESC_BOB, new AddCommand(expectedSupplier));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB2 + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + REMARK_DESC_BOB, new AddCommand(expectedSupplier));

        // multiple remarks - last remark accepted
        assertParseSuccess(parser, NAME_DESC_BOB2 + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_AMY
                + REMARK_DESC_BOB, new AddCommand(expectedSupplier));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        // missing type prefix

        // missing name prefix
        assertParseFailure(parser, TYPE_DESC_SUPPLIER + " " + VALID_NAME_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + REMARK_DESC_BOB,
                String.format(ParserUtil.MESSAGE_INVALID_TYPE, AddCommand.MESSAGE_USAGE));
        assertParseFailure(parser, TYPE_DESC_WAREHOUSE + " " + VALID_WAREHOUSE_NAME_A + PHONE_DESC_WAREHOUSE_A
                        + ADDRESS_DESC_WAREHOUSE_A + REMARK_DESC_WAREHOUSE_A,
                String.format(ParserUtil.MESSAGE_INVALID_TYPE, AddCommand.MESSAGE_USAGE));

        // missing phone prefix
        assertParseFailure(parser, TYPE_DESC_SUPPLIER + " " + NAME_DESC_BOB2 + VALID_PHONE_BOB
                        + EMAIL_DESC_BOB + REMARK_DESC_BOB,
                String.format(AddCommand.MESSAGE_SUPPLIER_MISSING_PREFIX, AddCommand.MESSAGE_USAGE));

        // all prefixes missing
        assertParseFailure(parser, TYPE_DESC_SUPPLIER + " " + VALID_NAME_BOB + VALID_PHONE_BOB
                        + VALID_EMAIL_BOB + VALID_REMARK_BOB,
                String.format(ParserUtil.MESSAGE_INVALID_TYPE, AddCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, TYPE_DESC_SUPPLIER + INVALID_NAME_DESC2 + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + REMARK_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, TYPE_DESC_SUPPLIER + NAME_DESC_BOB2 + INVALID_PHONE_DESC
                        + EMAIL_DESC_BOB + REMARK_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, TYPE_DESC_SUPPLIER + NAME_DESC_BOB2 + PHONE_DESC_BOB
                        + INVALID_EMAIL_DESC + REMARK_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS);

        // invalid remark
        assertParseFailure(parser, TYPE_DESC_SUPPLIER + NAME_DESC_BOB2 + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + INVALID_REMARK_DESC,
                Remark.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, TYPE_DESC_SUPPLIER + INVALID_NAME_DESC2 + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + INVALID_REMARK_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TYPE_DESC_SUPPLIER + NAME_DESC_BOB2 + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + REMARK_DESC_BOB,
                String.format(AddCommand.MESSAGE_SUPPLIER_MISSING_PREFIX, AddCommand.MESSAGE_USAGE));
    }

}
