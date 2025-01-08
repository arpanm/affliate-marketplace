import dayjs from 'dayjs';

export interface IBankDetails {
  id?: number;
  accountName?: string | null;
  accountNo?: string | null;
  bankName?: string | null;
  ifsc?: string | null;
  proofUrl?: string | null;
  upiHandle?: string | null;
  isActive?: boolean | null;
  createdBy?: string | null;
  createdOn?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  updatedOn?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<IBankDetails> = {
  isActive: false,
};
