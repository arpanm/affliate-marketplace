import dayjs from 'dayjs';
import { IAiToolSession } from 'app/shared/model/ai-tool-session.model';
import { PaymentStatus } from 'app/shared/model/enumerations/payment-status.model';
import { PgType } from 'app/shared/model/enumerations/pg-type.model';

export interface IAiToolPayment {
  id?: number;
  amount?: number | null;
  status?: keyof typeof PaymentStatus | null;
  paymentLinkUrl?: string | null;
  pgType?: keyof typeof PgType | null;
  pgId?: string | null;
  pgStatus?: string | null;
  pgRequestJson?: string | null;
  pgResponseJson?: string | null;
  pgRequestTimeStamp?: string | null;
  pgResponseTimeStamp?: string | null;
  isActive?: boolean | null;
  createdBy?: string | null;
  createdOn?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  updatedOn?: dayjs.Dayjs | null;
  session?: IAiToolSession | null;
}

export const defaultValue: Readonly<IAiToolPayment> = {
  isActive: false,
};
